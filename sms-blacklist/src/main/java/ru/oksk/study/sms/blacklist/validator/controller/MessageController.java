package ru.oksk.study.sms.blacklist.validator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oksk.study.common.dto.ExternalTransportSms;
import ru.oksk.study.sms.blacklist.validator.service.ProcessService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final ProcessService processService;
    private final Executor processExecutor;

    @Autowired
    public MessageController(ProcessService processService) {
        this.processService = processService;
        this.processExecutor = Executors.newSingleThreadExecutor();
    }

    @PostMapping
    public void startBlackListPoint(@RequestBody ExternalTransportSms externalTransportSms) {
        try {
            if(!validateTransportDto(externalTransportSms)){
                log.info("Not valid DTO with id {} come to service", externalTransportSms.getId());
            }
            log.info("Valid DTO: " + externalTransportSms);
            processExecutor.execute(() -> processService.processTransportMessage(externalTransportSms));
        }catch(Exception e){
            log.error("Exception " + e);
        }
    }

    private boolean validateTransportDto(ExternalTransportSms externalTransportSms) {
        return externalTransportSms.getOperatorId() != 0 &&
                externalTransportSms.getSessionName() != null &&
                externalTransportSms.getText() != null &&
                externalTransportSms.getPhone() != null &&
                externalTransportSms.getOriginatorId() != null &&
                externalTransportSms.getUri() != null;
    }
}