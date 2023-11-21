package ru.oksk.study.sms.server.dto;

import ru.oksk.study.sms.server.model.PriorityType;

public class SessionDto {
    private int id;
    private int operatorId;
    private PriorityType priorityType;
    private int providerId;
    private String sessionName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }
}
