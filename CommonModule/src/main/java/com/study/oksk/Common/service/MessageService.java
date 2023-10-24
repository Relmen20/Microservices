package com.study.oksk.Common.service;

import com.study.oksk.Common.dto.MessageDto;
import com.study.oksk.Common.mapper.MessageMapper;
import com.study.oksk.Common.repository.MessageRepository;
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
        return messageRepository.save(messageMapper.messageDtoToEntity(messageDto)).getId();
    }


    public void deleteById(String id){
        messageRepository.deleteById(id);
    }
}