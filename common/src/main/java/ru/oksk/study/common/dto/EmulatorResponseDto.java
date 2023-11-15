package ru.oksk.study.common.dto;

import ru.oksk.study.common.model.Error;
import ru.oksk.study.common.model.Status;

public class EmulatorResponseDto {

    private Status status;
    private Error error;

    public EmulatorResponseDto() {
    }

    public EmulatorResponseDto(Status status)
    {
        this.error = new Error();
        this.status = status;
    }

    public EmulatorResponseDto(Status status, Error error){
        this.error = error;
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
