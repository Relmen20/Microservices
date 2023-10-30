package ru.oksk.study.sms.blacklist.validator.service;

import ru.oksk.study.sms.blacklist.validator.dto.OriginatorBLDto;
import ru.oksk.study.sms.blacklist.validator.mapper.OriginatorBLMapper;
import ru.oksk.study.sms.blacklist.validator.repository.OriginatorBLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public OriginatorBLDto findByOriginatorId(int id){
        return originatorBLMapper.originatorBLEntityToDto(originatorBLRepository.findByOriginatorId(id));
    }

    public List<OriginatorBLDto> findAll() {
        return originatorBLRepository.findAll().stream()
                .map(originatorBLMapper::originatorBLEntityToDto)
                .collect(Collectors.toList());
    }

    public OriginatorBLDto findById(String id) {
        return originatorBLMapper.originatorBLEntityToDto(originatorBLRepository.findById(id).orElse(null));
    }

    public String save(OriginatorBLDto originatorBLDto) {
        return originatorBLRepository.save(originatorBLMapper.originatorBLDtoToEntity(originatorBLDto)).getId();
    }

    public void deleteById(String id){
        originatorBLRepository.deleteById(id);
    }
}
