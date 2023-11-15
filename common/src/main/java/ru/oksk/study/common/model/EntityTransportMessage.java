package ru.oksk.study.common.model;

import java.net.URI;
import java.util.List;

public class EntityTransportMessage {
    private String id;
    private String sessionName;
    private String phone;
    private String text;
    private String address;
    private int operatorId;
    private int originatorId;
    private int port;
    private URI uri;
    private List<Status> statusHistory;

    public EntityTransportMessage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(int originatorId) {
        this.originatorId = originatorId;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public List<Status> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<Status> statusHistory) {
        this.statusHistory = statusHistory;
    }

    @Override
    public String toString() {
        return "EntityTransportMessage{" +
                "id='" + id + '\'' +
                ", sessionName='" + sessionName + '\'' +
                ", phone='" + phone + '\'' +
                ", text='" + text + '\'' +
                ", address='" + address + '\'' +
                ", operatorId=" + operatorId +
                ", originatorId=" + originatorId +
                ", port=" + port +
                ", uri=" + uri +
                '}';
    }
}