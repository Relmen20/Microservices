package ru.oksk.study.sms.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.common.dto.MessageDto;
import ru.oksk.study.common.dto.SMS;
import ru.oksk.study.common.entity.MessageEntity;
import ru.oksk.study.common.mapper.MessageMapper;
import ru.oksk.study.common.model.SmsDto;
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

    public MessageDto processSmsBySessionName(SMS sms) throws NullSessionException {
        String sessionName = sms.getSessionName();
        SessionEntity sessionEntity = sessionService.findBySessionName(sessionName);
        if (sessionEntity == null) {
            throw new NullSessionException();
        }
        log.info("Validate by Postgres: " + sms);
        return mergeToMessageDto(sessionEntity, sms);
    }

    private MessageDto mergeToMessageDto(SessionEntity sessionEntity, SMS sms) {
        MessageDto messageDto = SMS.createMessageDto(sms);
        messageDto.setPort(sessionEntity.getProvider().getAddressEntity().getPort());
        messageDto.setAddress(sessionEntity.getProvider().getAddressEntity().getAddress());
        messageDto.setOperatorId(sessionEntity.getOperator().getId());
        messageDto.setStatusHistory(new ArrayList<>() {{
            add(new Status(StatusType.CREATE));
        }});
        return messageDto;
    }

    public void saveMessageInMongo(MessageDto messageDto) {
        MessageEntity entity = messageMapper.messageDtoToEntity(messageDto);
        String messageId = messageRepository.save(entity).getId();
        messageDto.setId(messageId);
        log.info("Entity saved in mongodb with id: " + messageId);
    }
}
