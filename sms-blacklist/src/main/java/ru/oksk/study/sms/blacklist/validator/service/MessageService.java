package ru.oksk.study.sms.blacklist.validator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.common.mapper.MessageMapper;
import ru.oksk.study.common.model.Error;
import ru.oksk.study.common.model.Status;
import ru.oksk.study.common.repository.MessageRepository;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void updateMessageStatus(String id, Status status) {
        messageRepository.updateStatus(id, status);
    }

    public void updateMessageStatus(String id, Status status, Error error) {
        messageRepository.updateStatus(id, status, error);
    }
}
