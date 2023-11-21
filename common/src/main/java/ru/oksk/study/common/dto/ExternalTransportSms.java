package ru.oksk.study.common.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Null;
import java.net.URI;

@Getter
@Setter
public class ExternalTransportSms extends SMS {

    @Null
    private URI uri;

    public ExternalTransportSms() {
    }

    @Override
    public String toString() {

        return super.toString()+ '\'' +
                ", uri='" + uri + "}";
    }
}