package ru.oksk.study.common.dto;

import ru.oksk.study.common.model.Error;
import ru.oksk.study.common.model.Status;

import java.time.LocalTime;

public class StatusDto {

    private Status status;
    private Error error;

    public StatusDto(Status status, Error error){
        this.error = error;
        this.status = status;
    }

    public StatusDto(Status status)
    {
        this.error = null;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
