package ru.oksk.study.sms.server.service;

import ru.oksk.study.sms.server.dto.OperatorDto;
import ru.oksk.study.sms.server.mapper.OperatorMapper;
import ru.oksk.study.sms.server.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperatorService {

    private final OperatorRepository operatorRepository;
    private final OperatorMapper operatorMapper;;

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
        return operatorMapper.operatorEntityToDto(operatorRepository.findById(id).orElse(null));
    }

    public int save(OperatorDto operatorDto){
        return operatorRepository.save(operatorMapper.operatorDtoToEntity(operatorDto)).getId();
    }

    public void deleteById(int id){
        operatorRepository.deleteById(id);
    }
}
