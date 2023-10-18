package com.study.oksk.mapper;

import com.study.oksk.dto.AddressDto;
import com.study.oksk.dto.OperatorDto;
import com.study.oksk.dto.ProviderDto;
import com.study.oksk.dto.SessionDto;
import com.study.oksk.entity.SessionEntity;
import com.study.oksk.model.PriorityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

    private final ProviderMapper providerMapper;
    private final OperatorMapper operatorMapper;
    private final AddressMapper addressMapper;

    @Autowired
    public SessionMapper(ProviderMapper providerMapper, OperatorMapper operatorMapper, AddressMapper addressMapper) {
        this.providerMapper = providerMapper;
        this.operatorMapper = operatorMapper;
        this.addressMapper = addressMapper;
    }

    public SessionDto sessionEntityToDto(SessionEntity sessionEntity){
        if(sessionEntity != null){
            SessionDto sessionDto = new SessionDto();
            sessionDto.setId(sessionEntity.getId());
            sessionDto.setSessionName(sessionEntity.getSessionName());
            sessionDto.setOperatorId(sessionEntity.getOperator().getId());
            sessionDto.setProviderId(sessionEntity.getProvider().getId());
            sessionDto.setPriorityType(sessionEntity.getPriorityType().name());
            return sessionDto;
        }else{
            return null;
        }
    }

    public SessionEntity sessionDtoToEntity(SessionDto sessionDto, OperatorDto operatorDto, ProviderDto providerDto, AddressDto addressDto) {
        if(sessionDto != null){
            SessionEntity sessionEntity = new SessionEntity();
            sessionEntity.setId(sessionDto.getId());
            sessionEntity.setSessionName(sessionDto.getSessionName());
            sessionEntity.setPriorityType(PriorityType.valueOf(sessionDto.getPriorityType()));

            sessionEntity.setProvider(providerMapper.providerDtoToEntity(providerDto,addressDto));
            sessionEntity.setOperator(operatorMapper.operatorDtoToEntity(operatorDto));
            return sessionEntity;
        }else{
            return null;
        }
    }
}
