package ru.oksk.study.common.service;

import ru.oksk.study.common.repository.MessageRepository;
import ru.oksk.study.common.dto.MessageDto;
import ru.oksk.study.common.entity.MessageEntity;
import ru.oksk.study.common.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    public List<MessageDto> findAll(){
        return messageRepository.findAll().stream()
                .map(messageMapper::messageEntityToDto)
                .collect(Collectors.toList());
    }

    public MessageDto findById(String id){
        return messageMapper.messageEntityToDto(messageRepository.findById(id).orElse(null));
    }

    public String save(MessageDto messageDto) {
        MessageEntity entity = messageMapper.messageDtoToEntity(messageDto);
        return messageRepository.save(entity).getId();
    }


    public void deleteById(String id){
        messageRepository.deleteById(id);
    }
}
