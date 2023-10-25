package ru.oksk.study.common.mapper;

import ru.oksk.study.common.dto.MessageDto;
import ru.oksk.study.common.entity.MessageEntity;
import ru.oksk.study.common.model.Status;
import org.springframework.stereotype.Component;

import static java.time.LocalTime.now;

@Component
public class MessageMapper {
    public MessageDto messageEntityToDto(MessageEntity messageEntity) {
        if(messageEntity == null){
            return null;
        }

        return new MessageDto.Builder()
                .withId(messageEntity.getId())
                .withPhone(messageEntity.getPhone())
                .withText(messageEntity.getText())
                .withOriginatorId(messageEntity.getOriginatorId())
                .withOperatorId(messageEntity.getOperatorId())
                .withSessionName(messageEntity.getSessionName())
                .withStatus(messageEntity.getStatus())
                .build();
    }

    public MessageEntity messageDtoToEntity(MessageDto messageDto) {
        MessageEntity messageEntity = new MessageEntity();
        if(messageDto != null){
            if(messageDto.getId() != null){
                messageEntity.setId(messageDto.getId());
            }
            messageEntity.setPhone(messageDto.getPhone());
            messageEntity.setText(messageDto.getText());
            messageEntity.setOriginatorId(messageDto.getOriginatorId());
            messageEntity.setOperatorId(messageDto.getOperatorId());
            messageEntity.setSessionName(messageDto.getSessionName());
            messageEntity.setStatus(messageDto.getStatus());
        }
        return messageEntity;
    }
}