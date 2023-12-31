package ru.oksk.study.sms.server.mapper;

import org.springframework.stereotype.Component;
import ru.oksk.study.sms.server.dto.AddressDto;
import ru.oksk.study.sms.server.entity.AddressEntity;

@Component
public class AddressMapper {

    public AddressDto addressEntityToDto(AddressEntity addressEntity) {
        if (addressEntity != null) {
            AddressDto addressDto = new AddressDto();
            addressDto.setId(addressEntity.getId());
            addressDto.setAddress(addressEntity.getAddress());
            addressDto.setPort(addressEntity.getPort());
            return addressDto;
        } else {
            return null;
        }
    }

    public AddressEntity addressDtoToEntity(AddressDto addressDto) {
        if (addressDto != null) {
            AddressEntity addressEntity = new AddressEntity();
            if (addressDto.getId() != 0) {
                addressEntity.setId(addressDto.getId());
            }
            addressEntity.setPort(addressDto.getPort());
            addressEntity.setAddress(addressDto.getAddress());
            return addressEntity;
        } else {
            return null;
        }
    }
}
