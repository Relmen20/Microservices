package ru.oksk.study.common.dto;

import lombok.Getter;
import lombok.Setter;
import ru.oksk.study.common.model.Status;

import java.util.List;

@Getter
@Setter
public class InnerAppSms extends SMS {
    private int port;
    private String address;
    private List<Status> statusHistory;

    public InnerAppSms() {
    }

    @Override
    public String toString() {

        return super.toString()+ '\'' +
                ", port='" + port + '\'' +
                ", address='" + address + '\'' +
                ", statusHistory='" + statusHistory + "}";
    }
}
