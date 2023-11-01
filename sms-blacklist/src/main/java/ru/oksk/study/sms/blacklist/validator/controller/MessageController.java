package ru.oksk.study.sms.blacklist.validator.controller;

import lombok.extern.slf4j.Slf4j;
import ru.oksk.study.common.dto.MutableSessionMessageDto;
import ru.oksk.study.sms.blacklist.validator.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> endPoint(@RequestBody MutableSessionMessageDto mutableSessionMessageDto){
        try{
            if(!validateMutableDto(mutableSessionMessageDto)){
                log.info("Not valid DTO come to service");
                return ResponseEntity.badRequest().build();
            }
            log.info("Valid DTO --> " + mutableSessionMessageDto.toString());
            return ResponseEntity.ok(processService.validateInMongo(mutableSessionMessageDto));
        }catch(Exception e){
            log.error("Exception " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean validateMutableDto(MutableSessionMessageDto mutableSessionMessageDto) {
        return mutableSessionMessageDto.getOperatorId() != 0 &&
                mutableSessionMessageDto.getSessionName() != null &&
                mutableSessionMessageDto.getAddress() != null &&
                mutableSessionMessageDto.getText() != null &&
                mutableSessionMessageDto.getPhone() != null &&
                mutableSessionMessageDto.getPort() != 0 &&
                mutableSessionMessageDto.getOriginatorId() != 0;
    }
}