package ru.oksk.study.sms.blacklist.validator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.common.dto.EmulatorResponseDto;
import ru.oksk.study.common.model.Error;
import ru.oksk.study.common.model.*;
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

    public void processTransportMessage(SmsDto smsDto) {
        String messageId = smsDto.getId();
        try {
            boolean isInOperatorBL = checkInOperatorBlackList(smsDto);
            boolean isInOriginatorBL = checkInOriginatorBlackList(smsDto);
            if (isInOperatorBL) {
                setUndeliveredStatus(messageId, ErrorType.BANNED_BY_OPERATOR);
                log.info("Message baned by operator blacklist");
                return;
            } else if (isInOriginatorBL) {
                setUndeliveredStatus(messageId, ErrorType.BANNED_BY_ORIGINATOR);
                log.info("Message baned by originator blacklist");
                return;
            }
            messageService.updateMessageStatus(messageId, new Status(StatusType.SUBMITTED));
            sendToEmulator(smsDto);
        } catch (Exception e) {
            log.error("Exception " + e);
        }
    }

    private void setUndeliveredStatus(String messageId, ErrorType errorType){
        messageService.updateMessageStatus(messageId, new Status(StatusType.UNDELIVERED), new Error(errorType));
    }

    private boolean checkInOperatorBlackList(SmsDto smsDto) {
        int operatorId = smsDto.getOperatorId();
        return operatorBLService.findByOperatorId(operatorId) != null;
    }

    private boolean checkInOriginatorBlackList(SmsDto smsDto) {
        String originatorId = smsDto.getOriginatorId();
        return originatorBLService.findByOriginatorId(originatorId) != null;
    }

    private void sendToEmulator(SmsDto smsDto) {
        String messageId = smsDto.getId();
        EmulatorResponseDto response;
        try {
            response = smsBlacklistFeignClient.sendToEmulator(smsDto.getUri(), smsDto);
            processResponse(messageId, response);
        } catch (Exception e) {
            messageService.updateMessageStatus(messageId,
                    new Status(StatusType.UNDELIVERED),
                    new Error(ErrorType.SERVER_NOT_AVAILABLE, e));
            log.error("Exception: {}, with messageID: {}", e.getMessage(), messageId);
        }
    }

    private void processResponse(String messageId, EmulatorResponseDto status) {
        if (status == null) {
            throw new RuntimeException("status == null");
        }
        if (status.getError().getCode() != 0) {
            messageService.updateMessageStatus(messageId, status.getStatus(), status.getError());
            log.info("Phone number with messageID: {} undelivered", messageId);
            return;
        }
        messageService.updateMessageStatus(messageId, status.getStatus());
        log.info("Message with messageID: {} delivered", messageId);
    }
}
