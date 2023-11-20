package ru.oksk.study.sms.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.sms.server.dto.AddressDto;
import ru.oksk.study.sms.server.dto.OperatorDto;
import ru.oksk.study.sms.server.dto.ProviderDto;
import ru.oksk.study.sms.server.dto.SessionDto;
import ru.oksk.study.sms.server.entity.SessionEntity;
import ru.oksk.study.sms.server.mapper.SessionMapper;
import ru.oksk.study.sms.server.repository.SessionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final ProviderService providerService;
    private final OperatorService operatorService;
    private final AddressService addressService;
    private final SessionMapper sessionMapper;

    @Autowired
    public SessionService(SessionRepository sessionRepository, ProviderService providerService, OperatorService operatorService, AddressService addressService, SessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.providerService = providerService;
        this.operatorService = operatorService;
        this.addressService = addressService;
        this.sessionMapper = sessionMapper;
    }

    public SessionDto findById(int id){
        SessionEntity sessionEntity = sessionRepository.findById(id).orElse(null);
        return sessionMapper.sessionEntityToDto(sessionEntity);
    }

    public List<SessionDto> findAll(){
        return sessionRepository.findAll().stream()
                .map(sessionMapper::sessionEntityToDto)
                .collect(Collectors.toList());
    }

    public int save(SessionDto sessionDto){
        OperatorDto operatorDto = operatorService.findById(sessionDto.getOperatorId());
        ProviderDto providerDto = providerService.findById(sessionDto.getProviderId());
        AddressDto addressDto = addressService.findById(providerDto.getAddressId());

        return sessionRepository.save(sessionMapper.sessionDtoToEntity(sessionDto, operatorDto, providerDto, addressDto)).getId();
    }

    public void deleteById(int id){
        sessionRepository.deleteById(id);
    }

    public SessionEntity findBySessionName(String sessionName) {
        return sessionRepository.findBySessionName(sessionName);
    }

}