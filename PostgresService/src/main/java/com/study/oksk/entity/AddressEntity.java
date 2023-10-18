package com.study.oksk.entity;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "port", nullable = false)
    private int port;
    @Column(name = "address", nullable = false)
    private String address;

    public AddressEntity(){}

    public AddressEntity(int id, int port, String address) {
        this.id = id;
        this.port = port;
        this.address = address;
    }

    public AddressEntity(int id) {
        this.id = id;
    }

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
        return "AddressEntity{" +
                "id=" + id +
                ", port=" + port +
                ", address='" + address + '\'' +
                '}';
    }
}
