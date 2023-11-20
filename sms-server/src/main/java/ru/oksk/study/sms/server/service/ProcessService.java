package ru.oksk.study.sms.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.common.dto.InnerAppSms;
import ru.oksk.study.common.dto.ExternalTransportSms;
import ru.oksk.study.sms.server.exception.NullSessionException;
import ru.oksk.study.sms.server.web.SmsServiceFeignClient;

import java.net.URI;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class ProcessService {
    private final MessageService messageService;
    private final SmsServiceFeignClient smsServiceFeignClient;
    private final Executor processServiceExecutor;

    @Autowired
    public ProcessService(MessageService messageService, SmsServiceFeignClient smsServiceFeignClient) {
        this.messageService = messageService;
        this.smsServiceFeignClient = smsServiceFeignClient;
        this.processServiceExecutor = Executors.newSingleThreadExecutor();
    }

    public void handleSMS(ExternalTransportSms externalTransportSms) {
        try {
            InnerAppSms innerAppSms = messageService.processSmsBySessionName(externalTransportSms);
            messageService.saveMessageInMongodb(innerAppSms);
            setUrl(externalTransportSms, innerAppSms);
            processServiceExecutor.execute(() -> smsServiceFeignClient.sendToBlacklist(externalTransportSms));
        }catch(NullSessionException e){
            log.error("Not valid session: " + externalTransportSms.getSessionName());
        }catch(Exception e){
            log.error("Exception: " + e);
        }
    }

    private void setUrl(ExternalTransportSms externalTransportSms, InnerAppSms innerAppSms){
        URI determinedBasePathUri = URI.create("http://" + innerAppSms.getAddress() +
                ":" + innerAppSms.getPort());
        externalTransportSms.setUri(determinedBasePathUri);
    }
}
