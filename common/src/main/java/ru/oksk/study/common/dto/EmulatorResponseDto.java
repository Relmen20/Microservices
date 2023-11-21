package ru.oksk.study.common.dto;

import lombok.Getter;
import lombok.Setter;
import ru.oksk.study.common.model.Error;
import ru.oksk.study.common.model.Status;

@Getter
@Setter
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

    @Override
    public String toString() {
        return "EmulatorResponseDto{" +
                "status=" + status +
                ", error=" + error +
                '}';
    }
}
