package ru.oksk.study.common.mapper;

import org.springframework.stereotype.Component;
import ru.oksk.study.common.dto.MessageDto;
import ru.oksk.study.common.entity.MessageEntity;
import ru.oksk.study.common.model.SmsDto;


@Component
public class MessageMapper {

    public MessageEntity messageDtoToEntity(MessageDto messageDto) {
        if (messageDto == null) {
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
                .build();
    }
}