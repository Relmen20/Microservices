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

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final ProcessService processService;

    @Autowired
    public MessageController(ProcessService processService) {
        this.processService = processService;
    }

    @PostMapping
    public ResponseEntity<String> startPoint(@Valid @RequestBody ClientMessageDto clientMessageDto){
        try{
            log.info("Valid clientMessageDto --> " + clientMessageDto.toString());
            ResponseEntity<String> response = processService.handleClientMessage(clientMessageDto);
            log.info("Response --> " + response.getStatusCode());
            return response;
        }catch(Exception e){
            log.error("Exception: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

}