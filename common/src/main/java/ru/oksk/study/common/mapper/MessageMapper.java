package ru.oksk.study.common.mapper;

import org.springframework.stereotype.Component;
import ru.oksk.study.common.dto.InnerAppSms;
import ru.oksk.study.common.entity.MessageEntity;


@Component
public class MessageMapper {

    public MessageEntity innerAppSmsToEntity(InnerAppSms innerAppSms) {
        if (innerAppSms == null) {
            return null;
        }
        return new MessageEntity.Builder()
                .withId(innerAppSms.getId())
                .withPhone(innerAppSms.getPhone())
                .withText(innerAppSms.getText())
                .withOriginatorId(innerAppSms.getOriginatorId())
                .withOperatorId(innerAppSms.getOperatorId())
                .withSessionName(innerAppSms.getSessionName())
                .withStatusHistory(innerAppSms.getStatusHistory())
                .build();
    }
}