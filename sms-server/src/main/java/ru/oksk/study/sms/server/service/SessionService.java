package ru.oksk.study.sms.server.service;

import ru.oksk.study.sms.server.mapper.SessionMapper;
import ru.oksk.study.sms.server.repository.SessionRepository;
import ru.oksk.study.sms.server.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return sessionMapper.sessionEntityToDto(sessionRepository.findById(id).orElse(null));
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

    public SessionValidateDto findBySessionName(String sessionName) {
        return sessionMapper.sessionEntityToSessionValidateDto(sessionRepository.findBySessionName(sessionName));
    }
}