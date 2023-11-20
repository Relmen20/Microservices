package ru.oksk.study.common.dto;

import ru.oksk.study.common.model.Status;
import ru.oksk.study.common.model.StatusType;

import java.util.ArrayList;
import java.util.List;

//FIXME: rename to SMSDto\MessageDto
public class MessageDto extends SMS {
    private int port;
    private String address;
    private List<Status> statusHistory;

    public MessageDto() {
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Status> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<Status> statusHistory) {
        this.statusHistory = statusHistory;
    }
}
