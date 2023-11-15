package ru.oksk.study.emulate.services.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.oksk.study.common.dto.EmulatorResponseDto;
import ru.oksk.study.common.model.Error;
import ru.oksk.study.common.model.*;

@Service
public class EmulatorService {
    @Value("${server.availabilityFactor}")
    private int availabilityFactor;

    public EmulatorResponseDto checkAvailability(EntityTransportMessage entityTransportMessage) {
        try{
            long phoneNumber = Long.parseLong(entityTransportMessage.getPhone());

            boolean availability = (phoneNumber % availabilityFactor) == 0;

            if(availability){
                return new EmulatorResponseDto(new Status(StatusType.DELIVERED));
            }else{
                return new EmulatorResponseDto(new Status(StatusType.REJECTED), new Error(ErrorType.INCORRECT_MOBILE_NUMBER));
            }

        }catch(Exception e){
            return new EmulatorResponseDto(new Status(StatusType.REJECTED), new Error(ErrorType.EXCEPTION, e));
        }

    }
}

