package com.example.aa1.controller;

import com.example.aa1.domain.Machine;
import com.example.aa1.exception.MachineNotFoundException;
import com.example.aa1.service.MachineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MachineController {

    private final MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }


    // GET (CON FILTRADO)


    @GetMapping("/machines")
    public ResponseEntity<List<Machine>> getAll(
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String manufacturer
    ) {
        return ResponseEntity.ok(
                machineService.findWithFilters(serialNumber, model, manufacturer)
        );
    }


    // GET BY ID


    @GetMapping("/machines/{id}")
    public ResponseEntity<Machine> getById(@PathVariable long id)
            throws MachineNotFoundException {

        return ResponseEntity.ok(
                machineService.findById(id)
        );
    }


    // POST

    @PostMapping("/machines")
    public ResponseEntity<Machine> add(
            @Valid @RequestBody Machine machine) {

        return new ResponseEntity<>(
                machineService.add(machine),
                HttpStatus.CREATED
        );
    }


    // PUT


    @PutMapping("/machines/{id}")
    public ResponseEntity<Machine> modify(
            @PathVariable long id,
            @Valid @RequestBody Machine machine)
            throws MachineNotFoundException {

        return ResponseEntity.ok(
                machineService.modify(id, machine)
        );
    }


    // PATCH


    @PatchMapping("/machines/{id}")
    public ResponseEntity<Machine> patch(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates)
            throws MachineNotFoundException {

        return ResponseEntity.ok(
                machineService.patch(id, updates)
        );
    }


    // DELETE


    @DeleteMapping("/machines/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id)
            throws MachineNotFoundException {

        machineService.delete(id);
        return ResponseEntity.noContent().build();
    }


    // JPQL


    @GetMapping("/machines/by-clinic/{clinicId}")
    public ResponseEntity<List<Machine>> getByClinic(
            @PathVariable Long clinicId) {

        return ResponseEntity.ok(
                machineService.findByClinicId(clinicId)
        );
    }
}
