package com.study.oksk.Common.mapper;

import com.study.oksk.Common.dto.MessageDto;
import com.study.oksk.Common.dto.MutableSessionMessageDto;
import com.study.oksk.Common.entity.MessageEntity;
import com.study.oksk.Common.model.Status;
import org.springframework.stereotype.Component;

import static java.time.LocalTime.now;

@Component
public class MessageMapper {
    public MessageDto messageEntityToDto(MessageEntity messageEntity) {
        MessageDto messageDto = new MessageDto();
        if(messageEntity != null){
            messageDto.setId(messageEntity.getId());
            messageDto.setPhone(messageEntity.getPhone());
            messageDto.setText(messageEntity.getText());
            messageDto.setOriginatorId(messageEntity.getOriginatorId());
            messageDto.setOperatorId(messageEntity.getOperatorId());
            messageDto.setSessionName(messageEntity.getSessionName());
            messageDto.setStatus(messageEntity.getStatus());
        }
        return messageDto;
    }

    public MessageEntity messageDtoToEntity(MessageDto messageDto) {
        MessageEntity messageEntity = new MessageEntity();
        if(messageDto != null){
            messageEntity.setPhone(messageDto.getPhone());
            messageEntity.setText(messageDto.getText());
            messageEntity.setOriginatorId(messageDto.getOriginatorId());
            messageEntity.setOperatorId(messageDto.getOperatorId());
            messageEntity.setSessionName(messageDto.getSessionName());
            if (messageDto.getStatus() == null){
                messageEntity.setStatus(new Status(now(), now(), now()));
            }else{
                messageEntity.setStatus(new Status(messageEntity.getStatus().getInit(),
                                                    now(),
                                                    messageEntity.getStatus().getUndelivered()));
            }
        }
        return messageEntity;
    }
}