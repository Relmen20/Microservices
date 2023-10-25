package ru.oksk.study.common.dto;

public class MutableSessionMessageDto {

    private String id;
    private int operatorId;
    private int originatorId;
    private int port;
    private String sessionName;
    private String address;
    private String phone;
    private String text;

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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    private MutableSessionMessageDto() {
    }

    public static class Builder {
        private String id;
        private String phone;
        private String text;
        private int originatorId;
        private int operatorId;
        private String sessionName;
        private String address;
        private int port;

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

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withPort(int port) {
            this.port = port;
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
            mutableDto.address = this.address;
            mutableDto.port = this.port;
            return mutableDto;
        }
    }
}
