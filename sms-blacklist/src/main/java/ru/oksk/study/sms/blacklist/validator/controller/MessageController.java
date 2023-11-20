package ru.oksk.study.sms.blacklist.validator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oksk.study.common.model.SmsDto;
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
    public void startBlackListPoint(@RequestBody SmsDto smsDto) {
        try {
            if(!validateTransportDto(smsDto)){
                log.info("Not valid DTO with id {} come to service", smsDto.getId());
            }
            log.info("Valid DTO: " + smsDto);
            processExecutor.execute(() -> processService.processTransportMessage(smsDto));
        }catch(Exception e){
            log.error("Exception " + e);
        }
    }

    private boolean validateTransportDto(SmsDto smsDto) {
        return smsDto.getOperatorId() != 0 &&
                smsDto.getSessionName() != null &&
                smsDto.getText() != null &&
                smsDto.getPhone() != null &&
                smsDto.getOriginatorId() != null &&
                smsDto.getUri() != null;
    }
}