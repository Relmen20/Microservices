package ru.oksk.study.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.net.URI;

@Getter
@Setter
@NoArgsConstructor
public abstract class SMS {
    @Null
    private String id;
    @NotNull
    @NotBlank
    private String phone;
    @NotNull
    @NotBlank
    private String originatorId;
    @NotNull
    @NotBlank
    private String text;
    @NotNull
    @NotBlank
    private String sessionName;
    private int operatorId;

    public static InnerAppSms createInnerAppSms(SMS sms){
        InnerAppSms innerAppSms = new InnerAppSms();
        innerAppSms.setId(sms.getId());
        innerAppSms.setSessionName(sms.getSessionName());
        innerAppSms.setPhone(sms.getPhone());
        innerAppSms.setText(sms.getText());
        innerAppSms.setOriginatorId(sms.getOriginatorId());
        innerAppSms.setOperatorId(sms.getOperatorId());
        return innerAppSms;
    }

    public static InnerAppSms createInnerAppSmsFromExternalTransportSms(ExternalTransportSms externalTransportSms) {
        InnerAppSms innerAppSms = new InnerAppSms();
        innerAppSms.setId(externalTransportSms.getId());
        innerAppSms.setSessionName(externalTransportSms.getSessionName());
        innerAppSms.setPhone(externalTransportSms.getPhone());
        innerAppSms.setText(externalTransportSms.getText());
        innerAppSms.setOriginatorId(externalTransportSms.getOriginatorId());
        innerAppSms.setOperatorId(externalTransportSms.getOperatorId());
        return innerAppSms;
    }

    @Override
    public String toString() {
        return "SMS{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", originatorId='" + originatorId + '\'' +
                ", text='" + text + '\'' +
                ", sessionName='" + sessionName + '\'' +
                '}';
    }
}
