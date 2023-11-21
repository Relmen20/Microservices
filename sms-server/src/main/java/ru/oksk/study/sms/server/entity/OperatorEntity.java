package ru.oksk.study.sms.server.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "operator", schema = "testdb")
public class OperatorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "operator_name")
    private String operatorName;
//    @OneToMany(mappedBy = "operatorEntity", cascade = CascadeType.ALL)
//    private List<SessionEntity> sessionEntityList;

    public OperatorEntity() {
    }

    public OperatorEntity(int id, String operatorName) {
        this.id = id;
        this.operatorName = operatorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String toString() {
        return "OperatorEntity{" +
                "id=" + id +
                ", operatorName='" + operatorName + '\'' + '}';
    }
}
