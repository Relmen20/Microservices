package ru.oksk.study.emulate.services.dto;

public class EmulatorDto {
    private String phone;

    public EmulatorDto(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
