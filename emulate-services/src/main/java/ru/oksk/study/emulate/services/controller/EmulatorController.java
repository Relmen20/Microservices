package ru.oksk.study.emulate.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oksk.study.common.dto.EmulatorOutputDto;
import ru.oksk.study.common.dto.StatusDto;
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
    public ResponseEntity<StatusDto> startEmulatorPoint(@RequestBody EmulatorOutputDto emulatorOutputDto){
        try{
            if(emulatorOutputDto.getPhone() == null){
                return ResponseEntity.badRequest().build();
            }

            StatusDto statusAfterCheck = emulatorService.checkAvailability(emulatorOutputDto);

            return ResponseEntity.ok(statusAfterCheck);
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
