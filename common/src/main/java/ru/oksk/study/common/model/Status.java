package ru.oksk.study.common.model;

import java.time.LocalTime;

public class Status {
    private LocalTime time;
    private StatusType name;

    public Status(LocalTime time, StatusType name) {
        this.time = time;
        this.name = name;
    }

    public Status(StatusType name){
        this.time = LocalTime.now();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Status{" +
                "time=" + time +
                ", name=" + name +
                '}';
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public StatusType getName() {
        return name;
    }

    public void setName(StatusType name) {
        this.name = name;
    }
}
