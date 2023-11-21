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
        return "{ id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", originatorId='" + originatorId + '\'' +
                ", text='" + text + '\'' +
                ", sessionName='" + sessionName + '\'' +
                ", operatorId='" + operatorId;
    }
}
