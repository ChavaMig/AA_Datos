package com.example.aa1.controller;

import com.example.aa1.domain.Clinic;
import com.example.aa1.domain.ClinicV2;
import com.example.aa1.dto.ClinicDto;
import com.example.aa1.dto.ClinicOutDto;
import com.example.aa1.exception.ClinicNotFoundException;
import com.example.aa1.service.ClinicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ClinicController {

    @Autowired
    private ClinicService clinicService;


    // ==================== V1 ====================


    @GetMapping("/v1/clinics")
    public ResponseEntity<List<ClinicOutDto>> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone
    ) {
        return ResponseEntity.ok(
                clinicService.findWithFilters(name, address, phone)
        );
    }

    @GetMapping("/v1/clinics/{id}")
    public ResponseEntity<ClinicDto> get(@PathVariable long id)
            throws ClinicNotFoundException {
        return ResponseEntity.ok(
                clinicService.findById(id)
        );
    }

    @PostMapping("/v1/clinics")
    public ResponseEntity<Clinic> addClinic(
            @Valid @RequestBody Clinic clinic) {
        return new ResponseEntity<>(
                clinicService.add(clinic),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/v1/clinics/{id}")
    public ResponseEntity<Clinic> modifyClinic(
            @PathVariable long id,
            @Valid @RequestBody Clinic clinic)
            throws ClinicNotFoundException {
        return ResponseEntity.ok(
                clinicService.modify(id, clinic)
        );
    }

    @PatchMapping("/v1/clinics/{id}")
    public ResponseEntity<Clinic> patchClinic(
            @PathVariable long id,
            @RequestBody Map<String, Object> updates)
            throws ClinicNotFoundException {
        return ResponseEntity.ok(
                clinicService.patch(id, updates)
        );
    }

    @DeleteMapping("/v1/clinics/{id}")
    public ResponseEntity<Void> deleteClinic(
            @PathVariable long id)
            throws ClinicNotFoundException {
        clinicService.delete(id);
        return ResponseEntity.noContent().build();
    }


    // ==================== V2 ====================


    @GetMapping("/v2/clinics")
    public ResponseEntity<List<ClinicV2>> getAllV2() {
        return ResponseEntity.ok(
                clinicService.findAllV2()
        );
    }

    @PostMapping("/v2/clinics")
    public ResponseEntity<ClinicV2> addClinicV2(
            @Valid @RequestBody ClinicV2 clinic) {
        return new ResponseEntity<>(
                clinicService.addV2(clinic),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/v2/clinics/{id}")
    public ResponseEntity<ClinicV2> modifyClinicV2(
            @PathVariable long id,
            @Valid @RequestBody ClinicV2 clinic)
            throws ClinicNotFoundException {
        return ResponseEntity.ok(
                clinicService.modifyV2(id, clinic)
        );
    }

    @DeleteMapping("/v2/clinics/{id}")
    public ResponseEntity<Void> deleteClinicV2(
            @PathVariable long id)
            throws ClinicNotFoundException {
        clinicService.deleteV2(id);
        return ResponseEntity.noContent().build();
    }
}