package ru.oksk.study.emulate.services.service;

import org.springframework.stereotype.Service;
import ru.oksk.study.emulate.services.dto.EmulatorDto;

@Service
public class EmulatorService {
    public String checkAvailability(EmulatorDto emulatorDto) {
        return "cool";
    }
}
