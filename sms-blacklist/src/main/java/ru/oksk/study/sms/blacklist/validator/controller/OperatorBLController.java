package ru.oksk.study.sms.blacklist.validator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oksk.study.sms.blacklist.validator.dto.OperatorBLDto;
import ru.oksk.study.sms.blacklist.validator.service.OperatorBLService;
import ru.oksk.study.sms.blacklist.validator.service.ProcessService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/operatorBL")
public class OperatorBLController {

    private final OperatorBLService operatorBLService;

    @Autowired
    public OperatorBLController(OperatorBLService operatorBLService, ProcessService processService) {
        this.operatorBLService = operatorBLService;
    }

    @GetMapping
    public ResponseEntity<List<OperatorBLDto>> findAll(){
        try{
            return ResponseEntity.ok(operatorBLService.findAll());
        }catch(Exception e){
            log.error("Exception " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OperatorBLDto> findById(@PathVariable String id){
        try{
            OperatorBLDto operatorBLDto = operatorBLService.findById(id);
            if(operatorBLDto == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(operatorBLDto);
        }catch(Exception e){
            log.error("Exception " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createOperatorBL(@RequestBody OperatorBLDto operatorBLDto){
        try{
            if(!validateOperatorBLDto(operatorBLDto) || operatorBLDto.getId() != null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(operatorBLService.save(operatorBLDto));
        }catch(Exception e){
            log.error("Exception " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> updateOperatorBL(@RequestBody OperatorBLDto operatorBLDto){
        try{
            if(!validateOperatorBLDto(operatorBLDto)){
                return ResponseEntity.badRequest().build();
            } else if (operatorBLService.findById(operatorBLDto.getId()) == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(operatorBLService.save(operatorBLDto));
        }catch(Exception e){
            log.error("Exception " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteOperatorBL(@PathVariable String id){
        try{
            operatorBLService.deleteById(id);
            return ResponseEntity.ok("");
        }catch (Exception e){
            log.error("Exception " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean validateOperatorBLDto(OperatorBLDto operatorBLDto){
        return operatorBLDto.getOperatorId() != 0;
    }
}
