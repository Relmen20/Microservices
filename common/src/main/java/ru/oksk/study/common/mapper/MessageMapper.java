package ru.oksk.study.common.mapper;

import org.springframework.stereotype.Component;
import ru.oksk.study.common.dto.MessageDto;
import ru.oksk.study.common.entity.MessageEntity;
import ru.oksk.study.common.model.EntityTransportMessage;


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
                .withStatusHistory(messageEntity.getStatusHistory())
                .withError(messageEntity.getErrorMessage())
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
                .withStatusHistory(messageDto.getStatusHistory())
                .withError(messageDto.getErrorMessage())
                .build();
    }

    public MessageEntity entityTransportMessageToEntity(EntityTransportMessage entityTransportMessage){
        if(entityTransportMessage == null){
            return null;
        }

        return new MessageEntity.Builder()
                .withId(entityTransportMessage.getId())
                .withPhone(entityTransportMessage.getPhone())
                .withText(entityTransportMessage.getText())
                .withOriginatorId(entityTransportMessage.getOriginatorId())
                .withOperatorId(entityTransportMessage.getOperatorId())
                .withSessionName(entityTransportMessage.getSessionName())
                .withStatusHistory(entityTransportMessage.getStatusHistory())
                .build();

    }
}