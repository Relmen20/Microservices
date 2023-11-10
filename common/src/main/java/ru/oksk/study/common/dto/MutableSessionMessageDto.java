package ru.oksk.study.common.dto;

import java.net.URI;

public class MutableSessionMessageDto {

    private String id;
    private String sessionName;
    private String phone;
    private String text;
    private int operatorId;
    private int originatorId;
    private URI uri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(int originatorId) {
        this.originatorId = originatorId;
    }


    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
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

    @Override
    public String toString() {
        return "MutableSessionMessageDto{" +
                "id='" + id + '\'' +
                ", sessionName='" + sessionName + '\'' +
                ", phone='" + phone + '\'' +
                ", text='" + text + '\'' +
                ", operatorId=" + operatorId +
                ", originatorId=" + originatorId +
                ", uri=" + uri +
                '}';
    }

    private MutableSessionMessageDto() {
    }

    public static class Builder {
        private String id;
        private String phone;
        private String text;
        private String sessionName;
        private int originatorId;
        private int operatorId;
        private URI uri;

        public Builder() {
        }

        public Builder withId(String id) {
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

        public Builder withUri(URI uri){
            this.uri = uri;
            return this;
        }

        public MutableSessionMessageDto build() {
            MutableSessionMessageDto mutableDto = new MutableSessionMessageDto();
            mutableDto.id = this.id;
            mutableDto.phone = this.phone;
            mutableDto.text = this.text;
            mutableDto.originatorId = this.originatorId;
            mutableDto.operatorId = this.operatorId;
            mutableDto.sessionName = this.sessionName;
            mutableDto.uri = this.uri;
            return mutableDto;
        }
    }
}
