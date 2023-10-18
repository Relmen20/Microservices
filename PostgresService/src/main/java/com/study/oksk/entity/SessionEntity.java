package com.study.oksk.entity;

import com.study.oksk.model.PriorityType;

import javax.persistence.*;

@Entity
@Table(name = "sessions")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "operator_id", referencedColumnName = "id")
    private OperatorEntity operatorEntity;
    @Column(name = "priorityType")
    private PriorityType priorityType;
    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    private ProviderEntity providerEntity;
    @Column(name = "session_name")
    private String sessionName;

    public SessionEntity(){}

    public SessionEntity(int id, OperatorEntity operatorEntity, PriorityType priorityType, ProviderEntity providerEntity, String sessionName) {
        this.id = id;
        this.operatorEntity = operatorEntity;
        this.priorityType = priorityType;
        this.providerEntity = providerEntity;
        this.sessionName = sessionName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OperatorEntity getOperator() {
        return operatorEntity;
    }

    public void setOperator(OperatorEntity operatorEntity) {
        this.operatorEntity = operatorEntity;
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    public ProviderEntity getProvider() {
        return providerEntity;
    }

    public void setProvider(ProviderEntity providerEntity) {
        this.providerEntity = providerEntity;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    @Override
    public String toString() {
        return "SessionEntity{" +
                "id=" + id +
                ", operatorEntity=" + operatorEntity.toString() +
                ", priorityType=" + priorityType.name() +
                ", providerEntity=" + providerEntity.toString() +
                ", sessionName='" + sessionName + '\'' +
                '}';
    }
}
