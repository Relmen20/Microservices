package ru.oksk.study.emulate.services.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.oksk.study.common.dto.EmulatorDto;

@Service
public class EmulatorService {
    @Value("${server.availabilityFactor}")
    private int availabilityFactor;

    public boolean checkAvailability(EmulatorDto emulatorDto) {
        System.out.println((emulatorDto.getPhone() % availabilityFactor) == 0);
        return (emulatorDto.getPhone() % availabilityFactor) == 0;
    }
}

