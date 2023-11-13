package ru.oksk.study.sms.blacklist.validator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import ru.oksk.study.common.dto.MutableSessionMessageDto;
import ru.oksk.study.common.dto.StatusDto;
import ru.oksk.study.common.model.Error;
import ru.oksk.study.common.model.ErrorType;
import ru.oksk.study.common.model.Status;
import ru.oksk.study.common.model.StatusType;
import ru.oksk.study.common.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.common.dto.EmulatorOutputDto;
import ru.oksk.study.sms.blacklist.validator.web.SmsBlacklistFeignClient;

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

    public void processMutableDto(MutableSessionMessageDto mutableSessionMessageDto) {


        //TODO: проверка того что метод работает в новом потоке
//        System.out.println("Working");
//        try{
//            for (int i = 0; i < 5; i++) {
//                Thread.sleep(500);
//                System.out.print(". ");
//                Thread.sleep(500);
//                System.out.print(". ");
//            }
//            System.out.println("");
//        }catch(Exception e){
//            log.error(e.getMessage());
//        }

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
        ResponseEntity<StatusDto> response;

        try {
            // TODO: вынести логику преобразования из метода отправки запроса
            //  + обработка ошибок по валидации номера телефона и конвертация его в LONG
            EmulatorOutputDto emulatorOutputDto = new EmulatorOutputDto.Builder()
                    .withPhone(mutableSessionMessageDto.getPhone())
                    .withMessage(mutableSessionMessageDto.getText())
                    .withOriginatorId(mutableSessionMessageDto.getOriginatorId())
                    .build();

            response = smsBlacklistFeignClient.startEmulatorPoint(mutableSessionMessageDto.getUri(), emulatorOutputDto);

            processResponse(messageId, response);

        } catch (Exception e) {
            messageService.updateMessageStatus(messageId, new Status(StatusType.UNDELIVERED));
            messageService.setError(messageId, new Error(ErrorType.SERVER_NOT_AVAILABLE, e));
            log.error("Exception: " + e);
        }
    }

    private void processResponse(String messageId, ResponseEntity<StatusDto> response){

        switch (response.getStatusCode()) {
            case OK:
                if (!response.hasBody()) {
                    break;
                }

                if (response.getBody().getError() == null) {
                    messageService.setError(messageId, response.getBody().getError());
                    log.info("Phone number was Rejected");
                    break;
                }

                messageService.updateMessageStatus(messageId, response.getBody().getStatus());
                log.info("Message delivered");
                break;

            default:
                messageService.updateMessageStatus(messageId, new Status(StatusType.UNKNOWN));
                messageService.setError(messageId, new Error(ErrorType.EXTERNAL_SERVER_ERROR));
                log.info(Objects.requireNonNull(response.getBody()).toString());
                break;
        }
    }
}
