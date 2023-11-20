package ru.oksk.study.sms.blacklist.validator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.sms.blacklist.validator.dto.OriginatorBLDto;
import ru.oksk.study.sms.blacklist.validator.entity.OriginatorsBLEntity;
import ru.oksk.study.sms.blacklist.validator.mapper.OriginatorBLMapper;
import ru.oksk.study.sms.blacklist.validator.repository.OriginatorBLRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OriginatorBLService {

    private final OriginatorBLRepository originatorBLRepository;
    private final OriginatorBLMapper originatorBLMapper;

    @Autowired
    public OriginatorBLService(OriginatorBLRepository originatorBLRepository, OriginatorBLMapper originatorBLMapper) {
        this.originatorBLRepository = originatorBLRepository;
        this.originatorBLMapper = originatorBLMapper;
    }

    public OriginatorBLDto findByOriginatorId(String id){
        OriginatorsBLEntity originatorsBLEntity = originatorBLRepository.findByOriginatorId(id);
        return originatorBLMapper.originatorBLEntityToDto(originatorsBLEntity);
    }

    public List<OriginatorBLDto> findAll() {
        return originatorBLRepository.findAll().stream()
                .map(originatorBLMapper::originatorBLEntityToDto)
                .collect(Collectors.toList());
    }

    public OriginatorBLDto findById(String id) {
        OriginatorsBLEntity originatorsBLEntity = originatorBLRepository.findById(id).orElse(null);
        return originatorBLMapper.originatorBLEntityToDto(originatorsBLEntity);
    }

    public String save(OriginatorBLDto originatorBLDto) {
        OriginatorsBLEntity originatorsBLEntity = originatorBLMapper.originatorBLDtoToEntity(originatorBLDto);
        return originatorBLRepository.save(originatorsBLEntity).getId();
    }

    public void deleteById(String id){
        originatorBLRepository.deleteById(id);
    }
}
