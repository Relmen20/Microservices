package ru.oksk.study.sms.blacklist.validator.dto;

public class EmulatorDto {
    private long phone;

    public EmulatorDto() {
    }

    public EmulatorDto(long phone) {
        this.phone = phone;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
