package ru.oksk.study.common.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.net.URI;

public class SMS {
    @Null
    private String id;
    @NotNull
    @NotBlank
    private String phone;
    @NotNull
    @NotBlank
    private String originatorId;
    @NotNull
    @NotBlank
    private String text;
    @NotNull
    @NotBlank
    private String sessionName;
    @Null
    private int operatorId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(String originatorId) {
        this.originatorId = originatorId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public static MessageDto createMessageDto(SMS sms){
        MessageDto messageDto = new MessageDto();
        messageDto.setId(sms.getId());
        messageDto.setSessionName(sms.getSessionName());
        messageDto.setPhone(sms.getPhone());
        messageDto.setText(sms.getText());
        messageDto.setOriginatorId(sms.getOriginatorId());
        messageDto.setOperatorId(sms.getOperatorId());
        return messageDto;
    }

    public static SmsDto createSMSDtoFromMessageDto(MessageDto messageDto){
        SmsDto smsDto = new SmsDto();
        smsDto.setId(messageDto.getId());
        smsDto.setSessionName(messageDto.getSessionName());
        smsDto.setPhone(messageDto.getPhone());
        smsDto.setText(messageDto.getText());
        smsDto.setOriginatorId(messageDto.getOriginatorId());
        smsDto.setOperatorId(messageDto.getOperatorId());
        URI determinedBasePathUri = URI.create("http://" + messageDto.getAddress() +
                ":" + messageDto.getPort());

        smsDto.setUri(determinedBasePathUri);
        return smsDto;
    }

    public static MessageDto createMessageDtoFromSmsDto(SmsDto smsDto) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(smsDto.getId());
        messageDto.setSessionName(smsDto.getSessionName());
        messageDto.setPhone(smsDto.getPhone());
        messageDto.setText(smsDto.getText());
        messageDto.setOriginatorId(smsDto.getOriginatorId());
        messageDto.setOperatorId(smsDto.getOperatorId());
        return messageDto;
    }

    @Override
    public String toString() {
        return "SMS{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", originatorId='" + originatorId + '\'' +
                ", text='" + text + '\'' +
                ", sessionName='" + sessionName + '\'' +
                '}';
    }
}
