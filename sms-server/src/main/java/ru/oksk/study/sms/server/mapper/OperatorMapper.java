package ru.oksk.study.sms.server.mapper;

import ru.oksk.study.sms.server.dto.OperatorDto;
import ru.oksk.study.sms.server.entity.OperatorEntity;
import org.springframework.stereotype.Component;

@Component
public class OperatorMapper {
    public OperatorDto operatorEntityToDto(OperatorEntity operatorEntity){
        if(operatorEntity != null){
            OperatorDto operatorDto = new OperatorDto();
            operatorDto.setId(operatorEntity.getId());
            operatorDto.setOperatorName(operatorEntity.getOperatorName());
            return operatorDto;
        }else{
            return null;
        }
    }

    public OperatorEntity operatorDtoToEntity(OperatorDto operatorDto){
        if(operatorDto != null){
            OperatorEntity operatorEntity = new OperatorEntity();
            if(operatorDto.getId() != 0){
                operatorEntity.setId(operatorDto.getId());
            }
            operatorEntity.setOperatorName(operatorDto.getOperatorName());
            return operatorEntity;
        }else{
            return null;
        }
    }

}
