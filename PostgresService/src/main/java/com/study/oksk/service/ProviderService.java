package com.study.oksk.service;

import com.study.oksk.dto.ProviderDto;
import com.study.oksk.entity.AddressEntity;
import com.study.oksk.entity.ProviderEntity;
import com.study.oksk.mapper.ProviderMapper;
import com.study.oksk.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return providerMapper.providerEntityToDto(providerRepository.findById(id).orElse(null));
    }

    public int save(ProviderDto providerDto){
        return providerRepository.save(providerMapper.providerDtoToEntity(providerDto,
                addressService.findById(providerDto.getAddressId()))).getId();
    }

    public void deleteById(int id){
        providerRepository.deleteById(id);
    }
}
