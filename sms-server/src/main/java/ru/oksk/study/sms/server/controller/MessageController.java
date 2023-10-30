package ru.oksk.study.sms.server.controller;

import ru.oksk.study.sms.server.dto.ClientMessageDto;
import ru.oksk.study.sms.server.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
             return processService.handleClientMessage(clientMessageDto);
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

}