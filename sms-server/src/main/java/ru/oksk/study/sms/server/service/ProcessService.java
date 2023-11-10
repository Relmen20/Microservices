package ru.oksk.study.sms.server.service;

import lombok.extern.slf4j.Slf4j;
import ru.oksk.study.common.dto.MessageDto;
import ru.oksk.study.common.dto.MutableSessionMessageDto;
import ru.oksk.study.common.model.Status;
import ru.oksk.study.common.model.StatusType;
import ru.oksk.study.common.service.MessageService;
import ru.oksk.study.sms.server.dto.ClientMessageDto;
import ru.oksk.study.sms.server.dto.SessionValidateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.oksk.study.sms.server.web.SmsServiceFeignClient;

import java.net.URI;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProcessService {

    private final MessageService messageService;
    private final SessionService sessionService;
    private final SmsServiceFeignClient smsServiceFeignClient;

    @Autowired
    public ProcessService(MessageService messageService,
                          SessionService sessionService,
                          SmsServiceFeignClient smsServiceFeignClient) {
        this.messageService = messageService;
        this.sessionService = sessionService;
        this.smsServiceFeignClient = smsServiceFeignClient;
    }

    public ResponseEntity<String> handleClientMessage(ClientMessageDto clientMessageDto) {
        try{
            SessionValidateDto sessionValidateDto = sessionService.findBySessionName(clientMessageDto.getSessionName());
            log.info("Validate by Postgres --> " + sessionValidateDto.toString());

            List<Status> statusHistory = new ArrayList();
            statusHistory.add(new Status(LocalTime.now(), StatusType.CREATE));

            String newId = messageService.save(new MessageDto.Builder()
                    .withPhone(clientMessageDto.getPhone())
                    .withText(clientMessageDto.getText())
                    .withOriginatorId(clientMessageDto.getOriginatorId())
                    .withOperatorId(sessionValidateDto.getOperatorId())
                    .withSessionName(sessionValidateDto.getSessionName())
                    .withStatusHistory(statusHistory)
                    .build());

            log.info("Entity saved in mongodb with id --> " + newId);

            URI determinedBasePathUri = URI.create("http://" + sessionValidateDto.getAddress() +
                    ":" + sessionValidateDto.getPort());

            MutableSessionMessageDto mutableSessionMessageDto = new MutableSessionMessageDto.Builder()
                    .withId(newId)
                    .withPhone(clientMessageDto.getPhone())
                    .withText(clientMessageDto.getText())
                    .withOriginatorId(clientMessageDto.getOriginatorId())
                    .withOperatorId(sessionValidateDto.getOperatorId())
                    .withSessionName(sessionValidateDto.getSessionName())
                    .withUri(determinedBasePathUri)
                    .build();

            return smsServiceFeignClient.endPoint(mutableSessionMessageDto);
        }catch(Exception e){
            log.error("Exception: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
