package ru.oksk.study.sms.server.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.oksk.study.sms.server.dto.AddressDto;
import ru.oksk.study.sms.server.dto.OperatorDto;
import ru.oksk.study.sms.server.dto.ProviderDto;
import ru.oksk.study.sms.server.dto.SessionDto;
import ru.oksk.study.sms.server.entity.SessionEntity;
import ru.oksk.study.sms.server.model.PriorityType;

@Component
public class SessionMapper {
    private final ProviderMapper providerMapper;
    private final OperatorMapper operatorMapper;

    @Autowired
    public SessionMapper(ProviderMapper providerMapper, OperatorMapper operatorMapper) {
        this.providerMapper = providerMapper;
        this.operatorMapper = operatorMapper;
    }

    public SessionDto sessionEntityToDto(SessionEntity sessionEntity) {
        if (sessionEntity != null) {
            SessionDto sessionDto = new SessionDto();
            sessionDto.setId(sessionEntity.getId());
            sessionDto.setSessionName(sessionEntity.getSessionName());
            sessionDto.setOperatorId(sessionEntity.getOperator().getId());
            sessionDto.setProviderId(sessionEntity.getProvider().getId());
            sessionDto.setPriorityType(sessionEntity.getPriorityType());
            return sessionDto;
        } else {
            return null;
        }
    }

    public SessionEntity sessionDtoToEntity(SessionDto sessionDto, OperatorDto operatorDto, ProviderDto providerDto, AddressDto addressDto) {
        if (sessionDto != null) {
            SessionEntity sessionEntity = new SessionEntity();
            sessionEntity.setId(sessionDto.getId());
            sessionEntity.setSessionName(sessionDto.getSessionName());
            sessionEntity.setPriorityType(sessionDto.getPriorityType());
            sessionEntity.setProvider(providerMapper.providerDtoToEntity(providerDto, addressDto));
            sessionEntity.setOperator(operatorMapper.operatorDtoToEntity(operatorDto));
            return sessionEntity;
        } else {
            return null;
        }
    }
}
