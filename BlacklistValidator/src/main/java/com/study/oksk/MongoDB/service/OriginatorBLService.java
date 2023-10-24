package com.study.oksk.MongoDB.service;

import com.study.oksk.MongoDB.dto.OriginatorBLDto;
import com.study.oksk.MongoDB.mapper.OriginatorBLMapper;
import com.study.oksk.MongoDB.repository.OriginatorBLRepository;
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

    public OriginatorBLDto findByPhone(String phone){
        return originatorBLMapper.originatorBLEntityToDto(originatorBLRepository.findByPhone(phone));
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
