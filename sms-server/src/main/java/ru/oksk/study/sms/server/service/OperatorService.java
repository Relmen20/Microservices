package ru.oksk.study.sms.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.oksk.study.sms.server.dto.OperatorDto;
import ru.oksk.study.sms.server.entity.OperatorEntity;
import ru.oksk.study.sms.server.mapper.OperatorMapper;
import ru.oksk.study.sms.server.repository.OperatorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperatorService {

    private final OperatorRepository operatorRepository;
    private final OperatorMapper operatorMapper;

    @Autowired
    public OperatorService(OperatorRepository operatorRepository, OperatorMapper operatorMapper) {
        this.operatorRepository = operatorRepository;
        this.operatorMapper = operatorMapper;
    }

    public List<OperatorDto> findAll(){
        return operatorRepository.findAll().stream()
                .map(operatorMapper::operatorEntityToDto)
                .collect(Collectors.toList());
    }

    public OperatorDto findById(int id){
        OperatorEntity operatorEntity = operatorRepository.findById(id).orElse(null);
        return operatorMapper.operatorEntityToDto(operatorEntity);
    }

    public int save(OperatorDto operatorDto){
        OperatorEntity operatorEntity = operatorMapper.operatorDtoToEntity(operatorDto);
        return operatorRepository.save(operatorEntity).getId();
    }

    public void deleteById(int id){
        operatorRepository.deleteById(id);
    }
}
