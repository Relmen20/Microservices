package ru.oksk.study.sms.blacklist.validator.controller;

import ru.oksk.study.sms.blacklist.validator.dto.OriginatorBLDto;
import ru.oksk.study.sms.blacklist.validator.service.OriginatorBLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/originatorBL")
public class OriginatorBLController {

    private final OriginatorBLService originatorBLService;

    @Autowired
    public OriginatorBLController(OriginatorBLService originatorBLService) {
        this.originatorBLService = originatorBLService;
    }

    @GetMapping
    public ResponseEntity<List<OriginatorBLDto>> findAll(){
        try{
            return ResponseEntity.ok(originatorBLService.findAll());
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OriginatorBLDto> findById(@PathVariable String id){
        try{
            OriginatorBLDto originatorBLDto = originatorBLService.findById(id);
            if(originatorBLDto == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(originatorBLDto);
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping
    public ResponseEntity<String> createOriginatorBL(@RequestBody OriginatorBLDto originatorBLDto){
        try{
            if(!validateOriginatorBLDto(originatorBLDto) || originatorBLDto.getId() != null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(originatorBLService.save(originatorBLDto));
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> updateOriginatorBL(@RequestBody OriginatorBLDto originatorBLDto){
        try{
            if(!validateOriginatorBLDto(originatorBLDto)){
                return ResponseEntity.badRequest().build();
            } else if (originatorBLService.findById(originatorBLDto.getId()) == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(originatorBLService.save(originatorBLDto));
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteOriginatorBL(@PathVariable String id){
        try{
            originatorBLService.deleteById(id);
            return ResponseEntity.ok("");
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean validateOriginatorBLDto(OriginatorBLDto originatorBLDto){
        return originatorBLDto.getOriginatorId() != 0;
    }
}
