package ru.oksk.study.sms.blacklist.validator.mapper;

import org.springframework.stereotype.Component;
import ru.oksk.study.sms.blacklist.validator.dto.OriginatorBLDto;
import ru.oksk.study.sms.blacklist.validator.entity.OriginatorsBLEntity;

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
        originatorsBLEntity.setId(originatorBLDto.getId());
        originatorsBLEntity.setOriginatorId(originatorBLDto.getOriginatorId());
        return originatorsBLEntity;
    }
}
