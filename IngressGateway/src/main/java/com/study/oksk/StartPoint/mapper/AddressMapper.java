package com.study.oksk.StartPoint.mapper;

import com.study.oksk.StartPoint.dto.AddressDto;
import com.study.oksk.StartPoint.entity.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper{

    public AddressDto addressEntityToDto(AddressEntity addressEntity){
        if (addressEntity != null) {
            AddressDto addressDto = new AddressDto();
            addressDto.setId(addressEntity.getId());
            addressDto.setAddress(addressEntity.getAddress());
            addressDto.setPort(addressEntity.getPort());
            return addressDto;
        }else{
            return null;
        }
    }

    public AddressEntity addressDtoToEntity(AddressDto addressDto){
        if(addressDto != null){
            AddressEntity addressEntity = new AddressEntity();
            if(addressDto.getId() != 0){
                addressEntity.setId(addressDto.getId());
            }
            addressEntity.setPort(addressDto.getPort());
            addressEntity.setAddress(addressDto.getAddress());
            return addressEntity;
        }else{
            return null;
        }
    }
}
