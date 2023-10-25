package ru.oksk.study.sms.blacklist.validator.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "operatorBLackList")
public class OperatorBLEntity {

    @Id
    private String id;
    private int operatorId;

    public OperatorBLEntity() {
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
