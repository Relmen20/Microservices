package ru.oksk.study.sms.blacklist.validator.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "originatorsBlackList")
public class OriginatorsBLEntity {

    @Id
    private String id;

    private String originatorId;

    public OriginatorsBLEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(String originatorId) {
        this.originatorId = originatorId;
    }
}
