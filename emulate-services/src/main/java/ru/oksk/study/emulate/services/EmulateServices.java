package ru.oksk.study.emulate.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.oksk.study.emulate.services.dto.EmulatorDto;

@SpringBootApplication
public class EmulateServices {
    public static void main(String[] args) {
        SpringApplication.run(EmulateServices.class, args);
    }
}