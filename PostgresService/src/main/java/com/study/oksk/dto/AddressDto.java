package com.study.oksk.dto;

public class AddressDto{

    private int id;

    private int port;

    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "AddressDto{" +
                "id=" + id +
                ", port=" + port +
                ", address='" + address + '\'' +
                '}';
    }
}
