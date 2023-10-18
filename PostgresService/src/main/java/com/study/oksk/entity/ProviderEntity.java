package com.study.oksk.entity;

import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provider")
public class ProviderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER) //#TODO состояния сущностей в hibernate и CascadeType
    @JoinColumn(name = "address_id", referencedColumnName = "id")
//    @OnDelete(action = CascadeType.ALL)
    private AddressEntity addressEntity;
    @Column(name = "provider_name")
    private String providerName;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "providerEntity", cascade = CascadeType.ALL)
    private List<SessionEntity> sessionEntityList;

    public ProviderEntity(){}

    public ProviderEntity(int id, AddressEntity addressEntity, String providerName, String email) {
        this.id = id;
        this.addressEntity = addressEntity;
        this.providerName = providerName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ProviderEntity{" +
                "id=" + id +
                ", addressEntity=" + addressEntity.toString() +
                ", providerName='" + providerName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public List<SessionEntity> getSessionEntityList() {
        return sessionEntityList;
    }

    public void setSessionEntityList(List<SessionEntity> sessionEntityList) {
        this.sessionEntityList = sessionEntityList;
    }

}
