package ru.oksk.study.common.dto;

import ru.oksk.study.common.dto.SMS;

import java.net.URI;
import java.util.List;

public class SmsDto extends SMS {
    private URI uri;

    public SmsDto() {
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}