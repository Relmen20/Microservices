package ru.oksk.study.sms.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.common.dto.MessageDto;
import ru.oksk.study.common.dto.SMS;
import ru.oksk.study.common.dto.SmsDto;
import ru.oksk.study.sms.server.exception.NullSessionException;
import ru.oksk.study.sms.server.web.SmsServiceFeignClient;

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

    public void handleSMS(SMS sms) {
        try {
            MessageDto messageDto = messageService.processSmsBySessionName(sms);
            messageService.saveMessageInMongo(messageDto);
            SmsDto smsDto = SMS.createSMSDtoFromMessageDto(messageDto);
            processServiceExecutor.execute(() -> smsServiceFeignClient.sendToBlacklist(smsDto));
        }catch(NullSessionException e){
            log.error("Not valid session: " + sms.getSessionName());
        }catch(Exception e){
            //FIXME поправить error message
            log.error("Exception: " + e);
        }
    }
}
