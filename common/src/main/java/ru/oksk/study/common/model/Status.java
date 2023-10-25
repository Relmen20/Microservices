package ru.oksk.study.common.model;

import java.time.LocalTime;

public class Status {
    private LocalTime init;
    private LocalTime delivered;
    private LocalTime undelivered;

    public Status(LocalTime init, LocalTime delivered, LocalTime undelivered) {
        this.init = init;
        this.delivered = delivered;
        this.undelivered = undelivered;
    }

    public LocalTime getInit() {
        return init;
    }

    public void setInit(LocalTime init) {
        this.init = init;
    }

    public LocalTime getDelivered() {
        return delivered;
    }

    public void setDelivered(LocalTime delivered) {
        this.delivered = delivered;
    }

    public LocalTime getUndelivered() {
        return undelivered;
    }

    public void setUndelivered(LocalTime undelivered) {
        this.undelivered = undelivered;
    }

    @Override
    public String toString() {
        return "Status{" +
                "init=" + init +
                ", delivered=" + delivered +
                ", undelivered=" + undelivered +
                '}';
    }
}
