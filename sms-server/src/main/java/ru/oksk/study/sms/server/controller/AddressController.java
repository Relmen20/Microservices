package ru.oksk.study.sms.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oksk.study.sms.server.dto.AddressDto;
import ru.oksk.study.sms.server.service.AddressService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping()
    public ResponseEntity<List<AddressDto>> findAll() {
        try {
            return ResponseEntity.ok(addressService.findAll());
        } catch (Exception e) {
            log.error("Exception " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable int id) {
        try {
            AddressDto addressDto = addressService.findById(id);
            if (addressDto != null) {
                return ResponseEntity.ok(addressDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Exception " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Integer> createAddress(@RequestBody AddressDto addressDto) {
        try {
            if (addressDto.getId() == 0 && validateAddressDto(addressDto)) {
                return ResponseEntity.ok(addressService.save(addressDto));
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Exception " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping()
    public ResponseEntity<Integer> updateAddress(@RequestBody AddressDto addressDto) {
        try {
            if (addressService.findById(addressDto.getId()) == null) {
                return ResponseEntity.notFound().build();
            } else if (!validateAddressDto(addressDto)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(addressService.save(addressDto));
        } catch (Exception e) {
            log.error("Exception " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable int id) {
        try {
            AddressDto addressDto = addressService.findById(id);
            if (addressDto != null) {
                addressService.deleteById(id);
                return ResponseEntity.ok(id);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Exception " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean validateAddressDto(AddressDto addressDto) {
        return addressDto.getPort() > 1025 &&
                addressDto.getPort() <= 65535 &&
                addressDto.getAddress() != null;
    }

    @PostMapping(path = "/all")
    public ResponseEntity<List<Integer>> createAll(@RequestBody ArrayList<AddressDto> listAddressDto) {
        try {
            if (listAddressDto.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(listAddressDto.stream()
                    .filter(addressDto -> validateAddressDto(addressDto) && addressDto.getId() == 0)
                    .map(addressService::save)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            log.error("Exception " + e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

