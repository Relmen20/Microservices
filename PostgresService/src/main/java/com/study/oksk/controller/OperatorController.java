package com.study.oksk.controller;

import com.study.oksk.dto.OperatorDto;
import com.study.oksk.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operator")
public class OperatorController {

    private final OperatorService operatorService;

    @Autowired
    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @GetMapping
    public ResponseEntity<List<OperatorDto>> findAll(){
        try{
            return ResponseEntity.ok(operatorService.findAll());
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OperatorDto> findById(@PathVariable int id){
        try{
            OperatorDto operatorDto = operatorService.findById(id);
            if(operatorDto != null){
                return ResponseEntity.ok(operatorDto);
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> createOperator(@RequestBody OperatorDto operatorDto){
        try{
            if(operatorDto.getId() == 0 && validateOperatorDto(operatorDto)){
                return ResponseEntity.ok(operatorService.save(operatorDto));
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable int id){
        try{
            OperatorDto operatorDto = operatorService.findById(id);
            if(operatorDto != null){
                operatorService.deleteById(id);
                return ResponseEntity.ok(operatorDto.getId());
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<Integer> updateOperator(@RequestBody OperatorDto operatorDto){
        try{
            if(operatorService.findById(operatorDto.getId()) == null){
                return ResponseEntity.notFound().build();
            }else if(validateOperatorDto(operatorDto)){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(operatorService.save(operatorDto));
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean validateOperatorDto(OperatorDto operatorDto) {
        return operatorDto.getOperatorName() != null;
    }


}
