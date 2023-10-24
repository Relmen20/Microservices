package com.study.oksk.StartPoint.service;

import com.study.oksk.Common.service.MessageService;
import com.study.oksk.StartPoint.dto.ClientMessageDto;
import com.study.oksk.Common.dto.MutableSessionMessageDto;
import com.study.oksk.StartPoint.dto.SessionValidateDto;
import com.study.oksk.StartPoint.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {

    private final MessageService messageService;
    private final SessionService sessionService;
    private final MessageMapper messageMapper;

    @Autowired
    public ProcessService(MessageService messageService,
                          SessionService sessionService,
                          MessageMapper messageMapper) {
        this.messageService = messageService;
        this.sessionService = sessionService;
        this.messageMapper = messageMapper;
    }


    public void startService(ClientMessageDto clientMessageDto) {
        SessionValidateDto sessionValidateDto = sessionService.findBySessionName(clientMessageDto.getSessionName());
        messageService.save(messageMapper.sessionValidateDtoToMessageEntity(sessionValidateDto, clientMessageDto));

        MutableSessionMessageDto mutableSessionMessageDto = messageMapper.createMutableDto(sessionValidateDto, clientMessageDto);

    }
}
