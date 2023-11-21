package ru.oksk.study.sms.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.common.dto.ExternalTransportSms;
import ru.oksk.study.common.dto.InnerAppSms;
import ru.oksk.study.common.dto.SMS;
import ru.oksk.study.common.entity.MessageEntity;
import ru.oksk.study.common.mapper.MessageMapper;
import ru.oksk.study.common.model.Status;
import ru.oksk.study.common.model.StatusType;
import ru.oksk.study.common.repository.MessageRepository;
import ru.oksk.study.sms.server.exception.NullSessionException;
import ru.oksk.study.sms.server.entity.SessionEntity;

import java.util.ArrayList;

@Slf4j
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final SessionService sessionService;

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper, SessionService sessionService) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.sessionService = sessionService;
    }

    public InnerAppSms processSmsBySessionName(ExternalTransportSms externalTransportSms) throws NullSessionException {
        String sessionName = externalTransportSms.getSessionName();
        SessionEntity sessionEntity = sessionService.findBySessionName(sessionName);
        if (sessionEntity == null) {
            throw new NullSessionException();
        }
        log.info("Validate by Postgres ");
        return mergeToInnerAppSms(sessionEntity, externalTransportSms);
    }

    private InnerAppSms mergeToInnerAppSms(SessionEntity sessionEntity, ExternalTransportSms externalTransportSms) {
        InnerAppSms innerAppSms = SMS.createInnerAppSmsFromExternalTransportSms(externalTransportSms);
        innerAppSms.setPort(sessionEntity.getProvider().getAddressEntity().getPort());
        innerAppSms.setAddress(sessionEntity.getProvider().getAddressEntity().getAddress());
        innerAppSms.setOperatorId(sessionEntity.getOperator().getId());
        innerAppSms.setStatusHistory(new ArrayList<>() {{
            add(new Status(StatusType.CREATE));
        }});
        return innerAppSms;
    }

    public void saveMessageInMongodb(InnerAppSms innerAppSms) {
        MessageEntity entity = messageMapper.innerAppSmsToEntity(innerAppSms);
        String messageId = messageRepository.save(entity).getId();
        innerAppSms.setId(messageId);
        log.info("Entity saved in mongodb with id: " + messageId);
    }
}
