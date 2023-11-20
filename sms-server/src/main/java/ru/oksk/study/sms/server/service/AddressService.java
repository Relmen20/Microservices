package ru.oksk.study.sms.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.sms.server.dto.AddressDto;
import ru.oksk.study.sms.server.entity.AddressEntity;
import ru.oksk.study.sms.server.mapper.AddressMapper;
import ru.oksk.study.sms.server.repository.AddressRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public List<AddressDto> findAll() {
        return addressRepository.findAll().stream()
                .map(addressMapper::addressEntityToDto)
                .collect(Collectors.toList());
    }

    public AddressDto findById(int id) {
        AddressEntity addressEntity = addressRepository.findById(id).orElse(null);
        return addressMapper.addressEntityToDto(addressEntity);
    }

    public int save(AddressDto addressDto) {
        AddressEntity addressEntity = addressMapper.addressDtoToEntity(addressDto);
        return addressRepository.save(addressEntity).getId();
    }

    public void deleteById(int id) {
        addressRepository.deleteById(id);
    }
}
