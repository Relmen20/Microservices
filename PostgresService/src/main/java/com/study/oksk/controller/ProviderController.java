package com.study.oksk.controller;

import com.study.oksk.dto.ProviderDto;
import com.study.oksk.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/provider")
public class ProviderController {

    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    public ResponseEntity<List<ProviderDto>> findAll(){
        try{
            return ResponseEntity.ok(providerService.findAll());
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ProviderDto> findById(@PathVariable int id){
        try{
            ProviderDto providerDto = providerService.findById(id);
            if(providerDto != null){
                return ResponseEntity.ok(providerDto);
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> createProvider(@RequestBody ProviderDto providerDto){
        try{
            if(providerDto.getId() == 0 && validateProviderDto(providerDto)){
                return ResponseEntity.ok(providerService.save(providerDto));
            }
            return ResponseEntity.badRequest().build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable int id){
        try{
            ProviderDto providerDto = providerService.findById(id);
            if(providerDto != null){
                providerService.deleteById(id);
                return ResponseEntity.ok(id);
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<Integer> updateProvider(@RequestBody ProviderDto providerDto){
        try{
            if(providerService.findById(providerDto.getId()) == null){
                return ResponseEntity.notFound().build();
            } else if (validateProviderDto(providerDto)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(providerService.save(providerDto));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean validateProviderDto(ProviderDto providerDto) {
        return providerDto.getAddressId() != 0 &&
               providerDto.getProviderName() != null &&
               providerDto.getEmail() != null;
    }
}
