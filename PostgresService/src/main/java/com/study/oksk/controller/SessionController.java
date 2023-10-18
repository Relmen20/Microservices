package com.study.oksk.controller;

import com.study.oksk.dto.SessionDto;
import com.study.oksk.model.PriorityType;
import com.study.oksk.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/session")
@Validated
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public ResponseEntity<List<SessionDto>> findAllSession(){
        try {
            return ResponseEntity.ok(sessionService.findAll());
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<SessionDto> findById(@PathVariable int id){
        try{
            SessionDto sessionDto = sessionService.findById(id);
            if(sessionDto != null){
                return ResponseEntity.ok(sessionDto);
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/findBySessionNme/{sessionName}")
    public ResponseEntity<SessionDto> findBySessionName(@PathVariable String sessionName){
        try{
            SessionDto sessionDto = sessionService.findBySessionName(sessionName);
            if(sessionDto != null){
                return ResponseEntity.ok(sessionDto);
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> createSession(@RequestBody SessionDto sessionDto){
        try{
            if(sessionDto.getId() == 0 && validateSessionDto(sessionDto)){
                return ResponseEntity.ok(sessionService.save(sessionDto));
            }
            return ResponseEntity.badRequest().build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<Integer> updateSession(@RequestBody SessionDto sessionDto){
        try{
            if(sessionService.findById(sessionDto.getId()) == null){
                return ResponseEntity.notFound().build();
            }else if(!validateSessionDto(sessionDto)){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(sessionService.save(sessionDto));
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable int id){
        try{
            SessionDto sessionDto = sessionService.findById(id);
            if(sessionDto != null){
                sessionService.deleteById(id);
                return ResponseEntity.ok(id);
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean validateSessionDto(SessionDto sessionDto) {
    return sessionDto.getOperatorId() != 0  &&
            sessionDto.getProviderId() != 0 &&
            sessionDto.getSessionName() != null &&
            (sessionDto.getPriorityType().equals(PriorityType.DEFAULT.name()) ||
             (sessionDto.getPriorityType().equals(PriorityType.VIP.name())));
    }
}