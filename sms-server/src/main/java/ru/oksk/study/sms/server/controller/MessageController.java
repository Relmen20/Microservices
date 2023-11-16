package ru.oksk.study.sms.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oksk.study.sms.server.dto.ClientMessageDto;
import ru.oksk.study.sms.server.service.ProcessService;

import javax.validation.Valid;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final ProcessService processService;
    private final Executor processControllerExecutor;

    @Autowired
    public MessageController(ProcessService processService) {
        this.processService = processService;
        this.processControllerExecutor = Executors.newSingleThreadExecutor();
    }

    @PostMapping
    public ResponseEntity<String> startPoint(@Valid @RequestBody ClientMessageDto clientMessageDto){
        try{
            processControllerExecutor.execute(() -> processService.handleClientMessage(clientMessageDto));
            log.info("Valid clientMessageDto --> " + clientMessageDto.toString());
            return ResponseEntity.ok("");
        }catch(Exception e){
            log.error("Exception: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

}