package com.study.oksk.mapper;

import com.study.oksk.dto.AddressDto;
import com.study.oksk.dto.ProviderDto;
import com.study.oksk.entity.AddressEntity;
import com.study.oksk.entity.ProviderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper {

    private final AddressMapper addressMapper;

    @Autowired
    public ProviderMapper(AddressMapper addressMapper){
        this.addressMapper = addressMapper;
    }

    public ProviderDto providerEntityToDto(ProviderEntity providerEntity){
        if(providerEntity != null){
            ProviderDto providerDto = new ProviderDto();
            providerDto.setId(providerEntity.getId());
            providerDto.setProviderName(providerEntity.getProviderName());
            providerDto.setAddressId(providerEntity.getAddressEntity().getId());
            providerDto.setEmail(providerEntity.getEmail());
            return providerDto;
        }else{
            return null;
        }
    }

    public ProviderEntity providerDtoToEntity(ProviderDto providerDto, AddressDto addressDto) {
        if(providerDto != null){
            ProviderEntity providerEntity = new ProviderEntity();
            if(providerDto.getId() != 0){
                providerEntity.setId(providerDto.getId());
            }
            providerEntity.setAddressEntity(addressMapper.addressDtoToEntity(addressDto));
            providerEntity.setProviderName(providerDto.getProviderName());
            providerEntity.setEmail(providerDto.getEmail());
            return providerEntity;
        }else{
            return null;
        }
    }
}
