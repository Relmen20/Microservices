package com.study.oksk.MongoDB.mapper;

import com.study.oksk.MongoDB.dto.OperatorBLDto;
import com.study.oksk.MongoDB.entity.OperatorBLEntity;
import org.springframework.stereotype.Component;

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
