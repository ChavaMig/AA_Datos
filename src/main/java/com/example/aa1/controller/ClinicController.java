package com.example.aa1.controller;

import com.example.aa1.domain.Clinic;
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


    // GET (CON FILTRADO)


    @GetMapping("/clinics")
    public ResponseEntity<List<ClinicOutDto>> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone
    ) {
        return ResponseEntity.ok(
                clinicService.findWithFilters(name, address, phone)
        );
    }


    // GET BY ID


    @GetMapping("/clinics/{id}")
    public ResponseEntity<ClinicDto> get(@PathVariable long id)
            throws ClinicNotFoundException {

        return ResponseEntity.ok(
                clinicService.findById(id)
        );
    }


    // POST


    @PostMapping("/clinics")
    public ResponseEntity<Clinic> addClinic(
            @Valid @RequestBody Clinic clinic) {

        return new ResponseEntity<>(
                clinicService.add(clinic),
                HttpStatus.CREATED
        );
    }


    // PUT


    @PutMapping("/clinics/{id}")
    public ResponseEntity<Clinic> modifyClinic(
            @PathVariable long id,
            @Valid @RequestBody Clinic clinic)
            throws ClinicNotFoundException {

        return ResponseEntity.ok(
                clinicService.modify(id, clinic)
        );
    }


    // PATCH


    @PatchMapping("/clinics/{id}")
    public ResponseEntity<Clinic> patchClinic(
            @PathVariable long id,
            @RequestBody Map<String, Object> updates)
            throws ClinicNotFoundException {

        return ResponseEntity.ok(
                clinicService.patch(id, updates)
        );
    }


    // DELETE
    // =====================

    @DeleteMapping("/clinics/{id}")
    public ResponseEntity<Void> deleteClinic(
            @PathVariable long id)
            throws ClinicNotFoundException {

        clinicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
