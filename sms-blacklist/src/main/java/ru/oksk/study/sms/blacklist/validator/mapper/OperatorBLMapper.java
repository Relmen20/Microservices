package ru.oksk.study.sms.blacklist.validator.mapper;

import org.springframework.stereotype.Component;
import ru.oksk.study.sms.blacklist.validator.dto.OperatorBLDto;
import ru.oksk.study.sms.blacklist.validator.entity.OperatorBLEntity;

@Component
public class OperatorBLMapper {

    public OperatorBLDto operatorBLEntityToDto(OperatorBLEntity operatorBLEntity){
        if(operatorBLEntity == null){
            return null;
        }
        OperatorBLDto operatorBLDto = new OperatorBLDto();
        operatorBLDto.setId(operatorBLEntity.getId());
        operatorBLDto.setOperatorId(operatorBLEntity.getOperatorId());
        return operatorBLDto;
    }

    public OperatorBLEntity operatorBLDtoToEntity (OperatorBLDto operatorBLDto){
        if(operatorBLDto == null){
            return null;
        }
        OperatorBLEntity operatorBLEntity = new OperatorBLEntity();
        operatorBLEntity.setId(operatorBLDto.getId());
        operatorBLEntity.setOperatorId(operatorBLDto.getOperatorId());
        return operatorBLEntity;
    }
}
