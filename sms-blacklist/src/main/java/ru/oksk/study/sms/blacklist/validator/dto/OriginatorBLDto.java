package ru.oksk.study.sms.blacklist.validator.dto;

public class OriginatorBLDto {
    private String id;
    private int originatorId;

    public int getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(int originatorId) {
        this.originatorId = originatorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
