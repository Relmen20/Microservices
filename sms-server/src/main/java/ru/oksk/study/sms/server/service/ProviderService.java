package ru.oksk.study.sms.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.sms.server.dto.ProviderDto;
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
