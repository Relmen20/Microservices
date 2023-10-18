package com.study.oksk.MongoDB.mapper;

import com.study.oksk.MongoDB.dto.OriginatorBLDto;
import com.study.oksk.MongoDB.entity.OriginatorsBLEntity;
import org.springframework.stereotype.Component;

@Component
public class OriginatorBLMapper {
    public OriginatorBLDto originatorBLEntityToDto(OriginatorsBLEntity originatorsBLEntity) {
        if(originatorsBLEntity == null){
            return null;
        }
        OriginatorBLDto originatorBLDto = new OriginatorBLDto();
        originatorBLDto.setOriginatorId(originatorsBLEntity.getOriginatorId());
        originatorBLDto.setPhone(originatorsBLEntity.getPhone());
        originatorBLDto.setId(originatorsBLEntity.getId());
        return originatorBLDto;
    }

    public OriginatorsBLEntity originatorBLDtoToEntity(OriginatorBLDto originatorBLDto) {
        if(originatorBLDto == null){
            return null;
        }
        OriginatorsBLEntity originatorsBLEntity = new OriginatorsBLEntity();
        originatorsBLEntity.setOriginatorId(originatorBLDto.getOriginatorId());
        originatorsBLEntity.setPhone(originatorBLDto.getPhone());
        return originatorsBLEntity;
    }
}
