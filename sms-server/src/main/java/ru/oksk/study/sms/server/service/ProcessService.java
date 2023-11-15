package ru.oksk.study.sms.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.oksk.study.common.model.EntityTransportMessage;
import ru.oksk.study.common.model.Status;
import ru.oksk.study.common.model.StatusType;
import ru.oksk.study.common.service.MessageService;
import ru.oksk.study.sms.server.dto.ClientMessageDto;
import ru.oksk.study.sms.server.web.SmsServiceFeignClient;

import java.net.URI;
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
            EntityTransportMessage entityTransportMessage = sessionService.findBySessionName(clientMessageDto.getSessionName());
            log.info("Validate by Postgres --> " + entityTransportMessage.toString());

            satisfyModelWithData(entityTransportMessage, clientMessageDto);

            String newId = messageService.save(entityTransportMessage);

            log.info("Entity saved in mongodb with id --> " + newId);

            entityTransportMessage.setId(newId);

            return smsServiceFeignClient.sendToBlacklist(entityTransportMessage);
        }catch(Exception e){
            log.error("Exception: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    private void satisfyModelWithData(EntityTransportMessage entityTransportMessage, ClientMessageDto clientMessageDto){
        List<Status> statusHistory = new ArrayList<>();
        statusHistory.add(new Status(StatusType.CREATE));

        URI determinedBasePathUri = URI.create("http://" + entityTransportMessage.getAddress() +
                ":" + entityTransportMessage.getPort());

        entityTransportMessage.setPhone(clientMessageDto.getPhone());
        entityTransportMessage.setText(clientMessageDto.getText());
        entityTransportMessage.setOriginatorId(clientMessageDto.getOriginatorId());
        entityTransportMessage.setUri(determinedBasePathUri);
        entityTransportMessage.setStatusHistory(statusHistory);
    }
}
