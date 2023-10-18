package com.study.oksk.service;

import com.study.oksk.dto.AddressDto;
import com.study.oksk.mapper.AddressMapper;
import com.study.oksk.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<AddressDto> findAll(){
        return addressRepository.findAll().stream()
                .map(addressMapper::addressEntityToDto)
                .collect(Collectors.toList());
    }

    public AddressDto findById(int id){
        return addressMapper.addressEntityToDto(addressRepository.findById(id).orElse(null));
    }

    public int save(AddressDto addressDto) {
        return addressRepository.save(addressMapper.addressDtoToEntity(addressDto)).getId();
    }

    public void deleteById(int id) {
        addressRepository.deleteById(id);
    }
}
