package ru.oksk.study.sms.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.sms.server.dto.AddressDto;
import ru.oksk.study.sms.server.dto.ProviderDto;
import ru.oksk.study.sms.server.entity.ProviderEntity;
import ru.oksk.study.sms.server.mapper.ProviderMapper;
import ru.oksk.study.sms.server.repository.ProviderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;
    private final AddressService addressService;


    @Autowired
    public ProviderService(ProviderRepository providerRepository, ProviderMapper providerMapper, AddressService addressService) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
        this.addressService = addressService;
    }

    public List<ProviderDto> findAll(){
        return providerRepository.findAll().stream()
                .map(providerMapper::providerEntityToDto)
                .collect(Collectors.toList());
    }

    public ProviderDto findById(int id){
        ProviderEntity providerEntity = providerRepository.findById(id).orElse(null);
        return providerMapper.providerEntityToDto(providerEntity);
    }

    public int save(ProviderDto providerDto){
        int providerAddressId = providerDto.getAddressId();
        AddressDto addressDto = addressService.findById(providerAddressId);
        ProviderEntity providerEntity = providerMapper.providerDtoToEntity(providerDto, addressDto);
        return providerRepository.save(providerEntity).getId();
    }

    public void deleteById(int id){
        providerRepository.deleteById(id);
    }
}
