package ru.oksk.study.common.mapper;

import ru.oksk.study.common.entity.MessageEntity;
import ru.oksk.study.common.dto.MessageDto;
import org.springframework.stereotype.Component;


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
        if(messageDto == null){
            return null;
        }

        return new MessageEntity.Builder()
                .withId(messageDto.getId())
                .withPhone(messageDto.getPhone())
                .withText(messageDto.getText())
                .withOriginatorId(messageDto.getOriginatorId())
                .withOperatorId(messageDto.getOperatorId())
                .withSessionName(messageDto.getSessionName())
                .withStatus(messageDto.getStatus())
                .build();
    }
}