package ru.oksk.study.sms.blacklist.validator.dto;

public class OperatorBLDto {

   private String id;
   private int operatorId;

    public OperatorBLDto() {
    }

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
}
