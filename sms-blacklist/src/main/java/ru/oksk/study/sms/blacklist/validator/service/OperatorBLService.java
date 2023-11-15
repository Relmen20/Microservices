package ru.oksk.study.sms.blacklist.validator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.sms.blacklist.validator.dto.OperatorBLDto;
import ru.oksk.study.sms.blacklist.validator.mapper.OperatorBLMapper;
import ru.oksk.study.sms.blacklist.validator.repository.OperatorBLRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperatorBLService {

    private final OperatorBLRepository operatorBLRepository;
    private final OperatorBLMapper operatorBLMapper;

    @Autowired
    public OperatorBLService(OperatorBLRepository operatorBLRepository, OperatorBLMapper operatorBLMapper) {
        this.operatorBLRepository = operatorBLRepository;
        this.operatorBLMapper = operatorBLMapper;
    }

    public OperatorBLDto findById(String id){
        return operatorBLMapper.operatorBLEntityToDto(operatorBLRepository.findById(id).orElse(null));
    }

    public OperatorBLDto findByOperatorId(int operatorId){
        return operatorBLMapper.operatorBLEntityToDto(operatorBLRepository.findByOperatorId(operatorId));
    }

    public List<OperatorBLDto> findAll(){
        return operatorBLRepository.findAll().stream()
                .map(operatorBLMapper::operatorBLEntityToDto)
                .collect(Collectors.toList());
    }

    public String save(OperatorBLDto operatorBLDto){
        return operatorBLRepository.save(operatorBLMapper.operatorBLDtoToEntity(operatorBLDto)).getId();
    }

    public void deleteById(String id){
        operatorBLRepository.deleteById(id);
    }
}
