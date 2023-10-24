package com.study.oksk.StartPoint.mapper;

import com.study.oksk.Common.dto.MessageDto;
import com.study.oksk.Common.dto.MutableSessionMessageDto;
import com.study.oksk.Common.model.Status;
import com.study.oksk.StartPoint.dto.ClientMessageDto;
import com.study.oksk.StartPoint.dto.SessionValidateDto;
import org.springframework.stereotype.Component;

import static java.time.LocalTime.now;

@Component
public class MessageMapper{

    public MessageDto sessionValidateDtoToMessageEntity(SessionValidateDto sessionValidateDto,
                                                        ClientMessageDto clientMessageDto) {
        MessageDto messageDto = new MessageDto();
        if(sessionValidateDto != null && clientMessageDto != null){
            messageDto.setPhone(clientMessageDto.getPhone());
            messageDto.setText(clientMessageDto.getText());
            messageDto.setOriginatorId(clientMessageDto.getOriginatorId());
            messageDto.setOperatorId(sessionValidateDto.getOperatorId());
            messageDto.setSessionName(sessionValidateDto.getSessionName());
            messageDto.setStatus(new Status(now(), now(), now()));
        }
        return messageDto;
    }

    public MutableSessionMessageDto createMutableDto(SessionValidateDto sessionValidateDto,
                                                     ClientMessageDto clientMessageDto){
        MutableSessionMessageDto mutableDto = new MutableSessionMessageDto();
        if(sessionValidateDto != null && clientMessageDto != null){
            mutableDto.setPhone(clientMessageDto.getPhone());
            mutableDto.setText(clientMessageDto.getText());
            mutableDto.setOriginatorId(clientMessageDto.getOriginatorId());
            mutableDto.setOperatorId(sessionValidateDto.getOperatorId());
            mutableDto.setSessionName(sessionValidateDto.getSessionName());
            mutableDto.setAddress(sessionValidateDto.getAddress());
            mutableDto.setPort(sessionValidateDto.getPort());
        }
        return mutableDto;
    }
}