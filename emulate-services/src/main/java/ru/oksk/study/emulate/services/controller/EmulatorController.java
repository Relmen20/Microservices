package ru.oksk.study.emulate.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oksk.study.emulate.services.dto.EmulatorDto;
import ru.oksk.study.emulate.services.service.EmulatorService;

@RestController
@RequestMapping(path = "/api")
public class EmulatorController {

    private final EmulatorService emulatorService;

    @Autowired
    public EmulatorController(EmulatorService emulatorService) {
        this.emulatorService = emulatorService;
    }

    @PostMapping
    public ResponseEntity<Boolean> startEmulatorPoint(@RequestBody EmulatorDto emulatorDto){
        try{
            if(emulatorDto.getPhone() == 0){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(emulatorService.checkAvailability(emulatorDto));
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
