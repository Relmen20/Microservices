package ru.oksk.study.sms.blacklist.validator.dto;

public class OriginatorBLDto {

    private String id;
    private String originatorId;

    public OriginatorBLDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(String originatorId) {
        this.originatorId = originatorId;
    }
}
