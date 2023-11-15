package ru.oksk.study.emulate.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oksk.study.common.dto.EmulatorResponseDto;
import ru.oksk.study.common.model.Error;
import ru.oksk.study.common.model.*;
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
    public EmulatorResponseDto startEmulatorPoint(@RequestBody EntityTransportMessage entityTransportMessage){
        try{
            if(entityTransportMessage.getPhone() == null){
                return new EmulatorResponseDto(new Status(StatusType.REJECTED),
                                    new Error(ErrorType.INCORRECT_MOBILE_NUMBER));
            }

            EmulatorResponseDto statusAfterCheck = emulatorService.checkAvailability(entityTransportMessage);

            return statusAfterCheck;
        }catch(Exception e){
            return null;
        }
    }
}
