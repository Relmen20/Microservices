package ru.oksk.study.common.dto;

public class EmulatorDto {
    private long phone;
    private String message;
    private int originatorId;

    public EmulatorDto() {
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(int originatorId) {
        this.originatorId = originatorId;
    }

    public static class Builder{
        private long phone;
        private String message;
        private int originatorId;

        public Builder(){
        }

        public Builder withPhone(long phone){
            this.phone = phone;
            return this;
        }

        public Builder withMessage(String message){
            this.message = message;
            return this;
        }

        public Builder withOriginatorId(int originatorId){
            this.originatorId = originatorId;
            return this;
        }

        public EmulatorDto build(){
            EmulatorDto emulatorDto = new EmulatorDto();
            emulatorDto.phone = this.phone;
            emulatorDto.message = this.message;
            emulatorDto.originatorId = this.originatorId;
            return emulatorDto;
        }
    }
}
