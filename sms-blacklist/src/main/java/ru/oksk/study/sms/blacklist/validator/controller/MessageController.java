package ru.oksk.study.sms.blacklist.validator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oksk.study.common.model.EntityTransportMessage;
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
    public void startBlackListPoint(@RequestBody EntityTransportMessage entityTransportMessage){

        try{
            if(!validateTransportDto(entityTransportMessage)){
                log.info("Not valid DTO come to service");
//                return ResponseEntity.badRequest().build();
            }

            log.info("Valid DTO --> " + entityTransportMessage);
            processExecutor.execute(() -> processService.processTransportMessage(entityTransportMessage));
//            return ResponseEntity.ok("");

        }catch(Exception e){
            log.error("Exception " + e);
//            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean validateTransportDto(EntityTransportMessage entityTransportMessage) {
        return entityTransportMessage.getOperatorId() != 0 &&
                entityTransportMessage.getSessionName() != null &&
                entityTransportMessage.getText() != null &&
                entityTransportMessage.getPhone() != null &&
                entityTransportMessage.getOriginatorId() != 0 &&
                entityTransportMessage.getUri() != null;
    }
}