package com.study.oksk.MongoDB.service;

import com.study.oksk.Common.dto.MessageDto;
import com.study.oksk.Common.dto.MutableSessionMessageDto;
import com.study.oksk.Common.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

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
        String response;

        if(operatorBLService.findByOperatorId(mutableSessionMessageDto.getOperatorId()) != null &&
           originatorBLService.findByOriginatorId(mutableSessionMessageDto.getOriginatorId()) != null &&
           originatorBLService.findByPhone(mutableSessionMessageDto.getPhone()) != null){
            response = "success";

//            MessageDto messageDto = messageService.findBy;
        }else{
            response = "banned";
        }
        return response;
    }
}
