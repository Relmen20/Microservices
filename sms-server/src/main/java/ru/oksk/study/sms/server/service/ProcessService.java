package ru.oksk.study.sms.server.service;

import lombok.extern.slf4j.Slf4j;
import ru.oksk.study.common.dto.MessageDto;
import ru.oksk.study.common.dto.MutableSessionMessageDto;
import ru.oksk.study.common.service.MessageService;
import ru.oksk.study.sms.server.dto.ClientMessageDto;
import ru.oksk.study.sms.server.dto.SessionValidateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.oksk.study.sms.server.web.ServiceFeignClient;

@Slf4j
@Service
public class ProcessService {

    private final MessageService messageService;
    private final SessionService sessionService;
    private final ServiceFeignClient serviceFeignClient;

    @Autowired
    public ProcessService(MessageService messageService,
                          SessionService sessionService,
                          ServiceFeignClient serviceFeignClient) {
        this.messageService = messageService;
        this.sessionService = sessionService;
        this.serviceFeignClient = serviceFeignClient;
    }

    public ResponseEntity<String> handleClientMessage(ClientMessageDto clientMessageDto) {
        try{
            SessionValidateDto sessionValidateDto = sessionService.findBySessionName(clientMessageDto.getSessionName());
            log.info("Validate by Postgres --> " + sessionValidateDto.toString());
            String newId = messageService.save(new MessageDto.Builder()
                    .withPhone(clientMessageDto.getPhone())
                    .withText(clientMessageDto.getText())
                    .withOriginatorId(clientMessageDto.getOriginatorId())
                    .withOperatorId(sessionValidateDto.getOperatorId())
                    .withSessionName(sessionValidateDto.getSessionName())
                    .build());

            log.info("Entity saved in mongodb with id --> " + newId);

            MutableSessionMessageDto mutableSessionMessageDto = new MutableSessionMessageDto.Builder()
                    .withId(newId)
                    .withPhone(clientMessageDto.getPhone())
                    .withText(clientMessageDto.getText())
                    .withOriginatorId(clientMessageDto.getOriginatorId())
                    .withOperatorId(sessionValidateDto.getOperatorId())
                    .withSessionName(sessionValidateDto.getSessionName())
                    .withAddress(sessionValidateDto.getAddress())
                    .withPort(sessionValidateDto.getPort())
                    .build();

            return serviceFeignClient.endPoint(mutableSessionMessageDto);
        }catch(Exception e){
            log.error("Exception: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
