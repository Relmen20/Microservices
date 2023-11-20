package ru.oksk.study.sms.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oksk.study.common.dto.SMS;
import ru.oksk.study.sms.server.exception.NullSessionException;
import ru.oksk.study.sms.server.service.ProcessService;
import javax.validation.Valid;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final ProcessService processService;
    private final Executor processControllerExecutor;

    @Autowired
    public MessageController(ProcessService processService) {
        this.processService = processService;
        this.processControllerExecutor = Executors.newSingleThreadExecutor();
    }

    @PostMapping
    //FIXME: receiveIncomeMassage
    public ResponseEntity<String> receiveIncomeMassage(@Valid @RequestBody SMS sms) {
        try {
            processControllerExecutor.execute(() -> processService.handleSMS(sms));
            log.info("Valid sms: " + sms.toString());
            return ResponseEntity.ok("");
        } catch (Exception e) {
            ////FIXME: код ошибки неинформативный
            log.error("Exception: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }
}