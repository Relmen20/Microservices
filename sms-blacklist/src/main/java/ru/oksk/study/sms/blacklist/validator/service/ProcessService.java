package ru.oksk.study.sms.blacklist.validator.service;

import lombok.extern.slf4j.Slf4j;
import ru.oksk.study.common.dto.MessageDto;
import ru.oksk.study.common.dto.MutableSessionMessageDto;
import ru.oksk.study.common.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Slf4j
@Service
public class ProcessService {

    private final OperatorBLService operatorBLService;
    private final OriginatorBLService originatorBLService;
    private final MessageService messageService;

    @Autowired
    public ProcessService(OperatorBLService operatorBLService, OriginatorBLService originatorBLService, MessageService messageService) {
        this.operatorBLService = operatorBLService;
        this.originatorBLService = originatorBLService;
        this.messageService = messageService;
    }

    public String validateInMongo(MutableSessionMessageDto mutableSessionMessageDto) {
        try{
            MessageDto messageDto = messageService.findById(mutableSessionMessageDto.getId());

            String response = "Init time: " + messageDto.getStatus().getInit() + "; ";
            if(operatorBLService.findByOperatorId(mutableSessionMessageDto.getOperatorId()) != null &&
               originatorBLService.findByOriginatorId(mutableSessionMessageDto.getOriginatorId()) != null){

                messageDto.getStatus().setUndelivered(LocalTime.now());
                response += "Undelivered time: " + messageDto.getStatus().getUndelivered() + "; ";
                log.info("Message baned by blacklist");
            }else{
                messageDto.getStatus().setDelivered(LocalTime.now());
                response += "Delivered time: " + messageDto.getStatus().getDelivered() + "; ";
                log.info("Message pass through blacklist");
            }

            messageService.save(messageDto);
            log.info("Update message complete");
            return response;
        }catch(Exception e){
            log.error("Exception " + e);
            return e.getMessage();
        }
    }
}
