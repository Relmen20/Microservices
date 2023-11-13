package ru.oksk.study.common.dto;

public class EmulatorOutputDto {

    private String phone;
    private String message;
    private int originatorId;

    public EmulatorOutputDto() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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
        private String phone;
        private String message;
        private int originatorId;

        public Builder(){
        }

        public Builder withPhone(String phone){
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

        public EmulatorOutputDto build(){
            EmulatorOutputDto emulatorOutputDto = new EmulatorOutputDto();
            emulatorOutputDto.phone = this.phone;
            emulatorOutputDto.message = this.message;
            emulatorOutputDto.originatorId = this.originatorId;
            return emulatorOutputDto;
        }
    }
}


