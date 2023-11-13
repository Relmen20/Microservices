package ru.oksk.study.emulate.services.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.oksk.study.common.dto.EmulatorDto;
import ru.oksk.study.common.dto.EmulatorOutputDto;
import ru.oksk.study.common.dto.StatusDto;
import ru.oksk.study.common.model.Error;
import ru.oksk.study.common.model.ErrorType;
import ru.oksk.study.common.model.Status;
import ru.oksk.study.common.model.StatusType;

@Service
public class EmulatorService {
    @Value("${server.availabilityFactor}")
    private int availabilityFactor;

    public StatusDto checkAvailability(EmulatorOutputDto emulatorOutputDto) {
        try{

            EmulatorDto emulatorDto = new EmulatorDto.Builder()
                    .withPhone(Long.parseLong(emulatorOutputDto.getPhone()))
                    .withMessage(emulatorOutputDto.getMessage())
                    .withOriginatorId(emulatorOutputDto.getOriginatorId())
                    .build();

            boolean availability = (emulatorDto.getPhone() % availabilityFactor) == 0;

            if(availability){
                return new StatusDto(new Status(StatusType.DELIVERED));
            }else{
                return new StatusDto(new Status(StatusType.REJECTED), new Error(ErrorType.INCORRECT_MOBILE_NUMBER));
            }

        }catch(Exception e){
            return new StatusDto(new Status(StatusType.REJECTED), new Error(ErrorType.EXCEPTION, e));
        }

    }
}

