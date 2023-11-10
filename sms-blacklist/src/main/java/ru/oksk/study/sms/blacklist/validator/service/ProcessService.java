package ru.oksk.study.sms.blacklist.validator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import ru.oksk.study.common.dto.MessageDto;
import ru.oksk.study.common.dto.MutableSessionMessageDto;
import ru.oksk.study.common.model.Error;
import ru.oksk.study.common.model.ErrorType;
import ru.oksk.study.common.model.Status;
import ru.oksk.study.common.model.StatusType;
import ru.oksk.study.common.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.sms.blacklist.validator.dto.EmulatorDto;
import ru.oksk.study.sms.blacklist.validator.web.SmsBlacklistFeignClient;

import java.time.LocalTime;
import java.util.Objects;

@Slf4j
@Service
public class ProcessService {

    private final SmsBlacklistFeignClient smsBlacklistFeignClient;

    private final OperatorBLService operatorBLService;
    private final OriginatorBLService originatorBLService;
    private final MessageService messageService;

    @Autowired
    public ProcessService(SmsBlacklistFeignClient smsBlacklistFeignClient, OperatorBLService operatorBLService,
                          OriginatorBLService originatorBLService, MessageService messageService) {
        this.smsBlacklistFeignClient = smsBlacklistFeignClient;
        this.operatorBLService = operatorBLService;
        this.originatorBLService = originatorBLService;
        this.messageService = messageService;
    }

    @Async
    public void processMutableDto(MutableSessionMessageDto mutableSessionMessageDto) {


        //TODO: проверка того что метод работает в новом потоке
        System.out.println("Working");
        try{
            for (int i = 0; i < 5; i++) {
                Thread.sleep(500);
                System.out.print(". ");
                Thread.sleep(500);
                System.out.print(". ");
            }
            System.out.println("");
        }catch(Exception e){
            log.error(e.getMessage());
        }

        String messageId = mutableSessionMessageDto.getId();
        try {

            boolean isInOperatorBL = checkInOperatorBlackList(mutableSessionMessageDto);
            boolean isInOriginatorBL = checkInOriginatorBlackList(mutableSessionMessageDto);

            if (isInOperatorBL) {
                log.info("Message baned by operator blacklist");
                return;
            } else if (isInOriginatorBL) {
                log.info("Message baned by originator blacklist");
                return;
            }

            messageService.updateMessageStatus(messageId, new Status(StatusType.SUBMITTED));

            sendToEmulator(mutableSessionMessageDto);

        } catch (Exception e) {
            log.error("Exception " + e);
        }
    }

    private boolean checkInOperatorBlackList(MutableSessionMessageDto mutableSessionMessageDto) {

        String messageId = mutableSessionMessageDto.getId();
        int operatorId = mutableSessionMessageDto.getOperatorId();

        if (operatorBLService.findByOperatorId(operatorId) != null) {
            messageService.updateMessageStatus(messageId, new Status(StatusType.UNDELIVERED));
            messageService.setError(messageId, new Error(ErrorType.BANNED_BY_OPERATOR));
            return true;
        }
        return false;
    }

    private boolean checkInOriginatorBlackList(MutableSessionMessageDto mutableSessionMessageDto) {

        String messageId = mutableSessionMessageDto.getId();
        int originatorId = mutableSessionMessageDto.getOriginatorId();

        if (originatorBLService.findByOriginatorId(originatorId) != null) {
            messageService.updateMessageStatus(messageId, new Status(StatusType.UNDELIVERED));
            messageService.setError(messageId, new Error(ErrorType.BANNED_BY_ORIGINATOR));
            return true;
        }
        return false;
    }

    private void sendToEmulator(MutableSessionMessageDto mutableSessionMessageDto) {

        String messageId = mutableSessionMessageDto.getId();
        ResponseEntity<Boolean> response;

        try {
            response = smsBlacklistFeignClient.startEmulatorPoint(mutableSessionMessageDto.getUri(),
                    new EmulatorDto(Long.parseLong(mutableSessionMessageDto.getPhone())));

            switch (response.getStatusCode()) {
                case OK:
                    if (!response.hasBody()) {
                        break;
                    }

                    if (Boolean.TRUE.equals(response.getBody())) {
                        messageService.updateMessageStatus(messageId, new Status(StatusType.DELIVERED));
                        log.info("Message delivered");
                    } else if (Boolean.FALSE.equals(response.getBody())) {
                        messageService.updateMessageStatus(messageId, new Status(StatusType.REJECTED));
                        messageService.setError(messageId, new Error(ErrorType.INCORRECT_MOBILE_NUMBER));
                        log.info("Phone number was Rejected");
                    }
                    break;

                default:
                    messageService.updateMessageStatus(messageId, new Status(StatusType.UNKNOWN));
                    messageService.setError(messageId, new Error(ErrorType.EXTERNAL_SERVER_ERROR));
                    log.info(Objects.requireNonNull(response.getBody()).toString());
                    break;
            }

        } catch (Exception e) {
            messageService.updateMessageStatus(messageId, new Status(StatusType.UNDELIVERED));
            messageService.setError(messageId, new Error(ErrorType.SERVER_NOT_AVAILABLE, e));
            log.error("Exception: " + e);
        }
    }
}
