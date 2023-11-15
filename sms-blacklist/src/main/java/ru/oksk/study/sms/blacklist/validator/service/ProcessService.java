package ru.oksk.study.sms.blacklist.validator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.common.dto.EmulatorResponseDto;
import ru.oksk.study.common.model.Error;
import ru.oksk.study.common.model.*;
import ru.oksk.study.common.service.MessageService;
import ru.oksk.study.sms.blacklist.validator.web.SmsBlacklistFeignClient;


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

    public void processMutableDto(EntityTransportMessage entityTransportMessage) {


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

        String messageId = entityTransportMessage.getId();
        try {

            boolean isInOperatorBL = checkInOperatorBlackList(entityTransportMessage);
            boolean isInOriginatorBL = checkInOriginatorBlackList(entityTransportMessage);

            if (isInOperatorBL) {
                messageService.updateMessageStatus(messageId, new Status(StatusType.UNDELIVERED));
                messageService.setError(messageId, new Error(ErrorType.BANNED_BY_OPERATOR));
                log.info("Message baned by operator blacklist");
                return;
            } else if (isInOriginatorBL) {
                messageService.updateMessageStatus(messageId, new Status(StatusType.UNDELIVERED));
                messageService.setError(messageId, new Error(ErrorType.BANNED_BY_ORIGINATOR));
                log.info("Message baned by originator blacklist");
                return;
            }

            messageService.updateMessageStatus(messageId, new Status(StatusType.SUBMITTED));

            sendToEmulator(entityTransportMessage);

        } catch (Exception e) {
            log.error("Exception " + e);
        }
    }

    private boolean checkInOperatorBlackList(EntityTransportMessage entityTransportMessage) {

        int operatorId = entityTransportMessage.getOperatorId();

        return operatorBLService.findByOperatorId(operatorId) != null;
    }

    private boolean checkInOriginatorBlackList(EntityTransportMessage entityTransportMessage) {

        int originatorId = entityTransportMessage.getOriginatorId();

        return originatorBLService.findByOriginatorId(originatorId) != null;
    }

    private void sendToEmulator(EntityTransportMessage entityTransportMessage) {

        String messageId = entityTransportMessage.getId();
        EmulatorResponseDto response;

        try {

            response = smsBlacklistFeignClient.sendToEmulator(entityTransportMessage.getUri(), entityTransportMessage);

            processResponse(messageId, response);

        } catch (Exception e) {
            messageService.updateMessageStatus(messageId, new Status(StatusType.UNDELIVERED));
            messageService.setError(messageId, new Error(ErrorType.SERVER_NOT_AVAILABLE, e));
            log.error("Exception: " + e);
        }
    }

    private void processResponse(String messageId, EmulatorResponseDto status){

        if (status == null) {
            throw new RuntimeException("status == null");
        }

        messageService.updateMessageStatus(messageId, status.getStatus());

        if (status.getError().getCode() != 0) {
            messageService.setError(messageId, status.getError());
            log.info("Phone number was Rejected");
            return;
        }

        log.info("Message delivered");
    }
}
