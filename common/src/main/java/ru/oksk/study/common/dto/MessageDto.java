package ru.oksk.study.common.dto;

import ru.oksk.study.common.model.Status;

import java.time.LocalTime;

public class MessageDto {
    private String id;
    private String phone;
    private String text;
    private int originatorId;
    private int operatorId;
    private String sessionName;
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(int originatorId) {
        this.originatorId = originatorId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private MessageDto(){}

    public static class Builder {
        private String id;
        private String phone;
        private String text;
        private int originatorId;
        private int operatorId;
        private String sessionName;
        private Status status;

        public Builder() {
        }

        public Builder withId(String id){
            this.id = id;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withOriginatorId(int originatorId) {
            this.originatorId = originatorId;
            return this;
        }

        public Builder withOperatorId(int operatorId) {
            this.operatorId = operatorId;
            return this;
        }

        public Builder withSessionName(String sessionName) {
            this.sessionName = sessionName;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public MessageDto build() {
            MessageDto messageDto = new MessageDto();
            messageDto.id = this.id;
            messageDto.phone = this.phone;
            messageDto.text = this.text;
            messageDto.originatorId = this.originatorId;
            messageDto.operatorId = this.operatorId;
            messageDto.sessionName = this.sessionName;
            LocalTime time = LocalTime.now();
            messageDto.status = this.status == null ? new Status(time, time, time) : this.status;
            return messageDto;
        }
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", text='" + text + '\'' +
                ", originatorId=" + originatorId +
                ", operatorId=" + operatorId +
                ", sessionName='" + sessionName + '\'' +
                ", status=" + status +
                '}';
    }
}
