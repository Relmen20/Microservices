package ru.oksk.study.sms.server.dto;

public class SessionValidateDto {

    private int operatorId;
    private String sessionName;
    private String address;
    private int port;

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "SessionValidateDto{" +
                "operatorId=" + operatorId +
                ", sessionName='" + sessionName + '\'' +
                ", address='" + address + '\'' +
                ", port=" + port +
                '}';
    }
}
