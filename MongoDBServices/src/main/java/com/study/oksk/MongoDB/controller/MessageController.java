package com.study.oksk.MongoDB.controller;

import com.study.oksk.MongoDB.dto.MessageDto;
import com.study.oksk.MongoDB.entity.MessageEntity;
import com.study.oksk.MongoDB.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<MessageDto>> findAll(){
        try{
            return ResponseEntity.ok(messageService.findAll());
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<MessageDto> findById(@PathVariable String id){
        try{
            MessageDto messageDto = messageService.findById(id);
            if(messageDto == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(messageDto);
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createMessage(@RequestBody MessageDto messageDto){
        try{
            if(!validateMessageDto(messageDto) || messageDto.getId() != null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(messageService.save(messageDto));
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> updateMessage(@RequestBody MessageDto messageDto){
        try{
            if(!validateMessageDto(messageDto)){
                return ResponseEntity.badRequest().build();
            } else if (messageService.findById(messageDto.getId()) == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(messageService.save(messageDto));
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable String id){
        try{
            messageService.deleteById(id);
            return ResponseEntity.ok("");
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean validateMessageDto(MessageDto messageDto){
        return messageDto.getPhone() != null &&
                messageDto.getOriginatorId() != 0 &&
                messageDto.getOperatorId() != 0 &&
                messageDto.getText() != null &&
                messageDto.getSessionName() != null &&
                messageDto.getStatus() == null;
    }
}