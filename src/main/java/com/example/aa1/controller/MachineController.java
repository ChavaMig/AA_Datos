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

    @GetMapping("/machines")
    public ResponseEntity<List<Machine>> getAll() {
        return ResponseEntity.ok(machineService.findAll());
    }

    @GetMapping("/machines/{id}")
    public ResponseEntity<Machine> getById(@PathVariable long id) throws MachineNotFoundException {
        return ResponseEntity.ok(machineService.findById(id));
    }

    @GetMapping("/machines/by-manufacturer")
    public ResponseEntity<List<Machine>> getByManufacturer(@RequestParam String manufacturer) {
        return ResponseEntity.ok(machineService.findByManufacturer(manufacturer));
    }

    @PostMapping("/machines")
    public ResponseEntity<Machine> add(@Valid @RequestBody Machine machine) {
        return new ResponseEntity<>(machineService.add(machine), HttpStatus.CREATED);
    }

    @PutMapping("/machines/{id}")
    public ResponseEntity<Machine> modify(@PathVariable long id, @RequestBody Machine machine)
            throws MachineNotFoundException {
        return ResponseEntity.ok(machineService.modify(id, machine));
    }

    // ✅ PATCH
    @PatchMapping("/machines/{id}")
    public ResponseEntity<Machine> patch(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates)
            throws MachineNotFoundException {

        return ResponseEntity.ok(machineService.patch(id, updates));
    }

    @DeleteMapping("/machines/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) throws MachineNotFoundException {
        machineService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MachineNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMachineNotFound(MachineNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

}
