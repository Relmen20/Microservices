package com.study.oksk.Common.entity;

import com.study.oksk.Common.model.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "message")
public class MessageEntity {

    @Id
    private String id;
    private String phone;
    private String text;
    private int originatorId;
    private int OperatorId;
    private String sessionName;
    private Status status;

    public MessageEntity() {
    }

    public String getId() {
        return id;
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

    public int getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(int originatorId) {
        this.originatorId = originatorId;
    }

    public int getOperatorId() {
        return OperatorId;
    }

    public void setOperatorId(int operatorId) {
        OperatorId = operatorId;
    }
    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
