package ru.oksk.study.common.entity;

import com.mongodb.lang.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.oksk.study.common.model.Error;
import ru.oksk.study.common.model.Status;

import java.util.List;

@Document(value = "message")
public class MessageEntity {

    @Id
    private String id;
    private String phone;
    private String text;
    private int originatorId;
    private int operatorId;
    private String sessionName;
    private List<Status> statusHistory;
    @Nullable
    private Error errorMessage;

    public MessageEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(int originatorId) {
        this.originatorId = originatorId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public List<Status> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<Status> status) {
        this.statusHistory = status;
    }

    public Error getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Error errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static class Builder{

        private String id;
        private String phone;
        private String text;
        private int originatorId;
        private int operatorId;
        private String sessionName;
        private List<Status> statusHistory;
        private Error errorMessage;
        public Builder() {
        }

        public Builder withId(String id){
            this.id = id;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withOriginatorId(int originatorId) {
            this.originatorId = originatorId;
            return this;
        }

        public Builder withOperatorId(int operatorId) {
            this.operatorId = operatorId;
            return this;
        }

        public Builder withSessionName(String sessionName) {
            this.sessionName = sessionName;
            return this;
        }

        public Builder withStatusHistory(List<Status> status) {
            this.statusHistory = status;
            return this;
        }

        public Builder withError(Error errorMessage){
            this.errorMessage = errorMessage;
            return this;
        }

        public MessageEntity build() {
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.id = this.id;
            messageEntity.phone = this.phone;
            messageEntity.text = this.text;
            messageEntity.originatorId = this.originatorId;
            messageEntity.operatorId = this.operatorId;
            messageEntity.sessionName = this.sessionName;
            messageEntity.statusHistory = this.statusHistory;
            messageEntity.errorMessage = this.errorMessage == null ? null : this.errorMessage;
            return messageEntity;
        }

    }
}
