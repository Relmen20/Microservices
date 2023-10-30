package ru.oksk.study.sms.blacklist.validator.mapper;

import ru.oksk.study.sms.blacklist.validator.dto.OriginatorBLDto;
import ru.oksk.study.sms.blacklist.validator.entity.OriginatorsBLEntity;
import org.springframework.stereotype.Component;

@Component
public class OriginatorBLMapper {
    public OriginatorBLDto originatorBLEntityToDto(OriginatorsBLEntity originatorsBLEntity) {
        if(originatorsBLEntity == null){
            return null;
        }
        OriginatorBLDto originatorBLDto = new OriginatorBLDto();
        originatorBLDto.setOriginatorId(originatorsBLEntity.getOriginatorId());
        originatorBLDto.setId(originatorsBLEntity.getId());
        return originatorBLDto;
    }

    public OriginatorsBLEntity originatorBLDtoToEntity(OriginatorBLDto originatorBLDto) {
        if(originatorBLDto == null){
            return null;
        }
        OriginatorsBLEntity originatorsBLEntity = new OriginatorsBLEntity();
        originatorsBLEntity.setOriginatorId(originatorBLDto.getOriginatorId());
        return originatorsBLEntity;
    }
}
