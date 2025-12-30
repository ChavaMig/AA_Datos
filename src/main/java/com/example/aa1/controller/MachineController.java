package com.example.aa1.controller;

import com.example.aa1.domain.Machine;
import com.example.aa1.exception.MachineNotFoundException;
import com.example.aa1.service.MachineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MachineController {

    @Autowired
    private MachineService machineService;

    // GET /machines
    @GetMapping("/machines")
    public ResponseEntity<List<Machine>> getAll() {
        List<Machine> machines = machineService.findAll();
        return ResponseEntity.ok(machines);
    }

    // GET /machines/{id}
    @GetMapping("/machines/{id}")
    public ResponseEntity<Machine> getById(@PathVariable long id) throws MachineNotFoundException {
        Machine machine = machineService.findById(id);
        return ResponseEntity.ok(machine);
    }

    // POST /machines
    @PostMapping("/machines")
    public ResponseEntity<Machine> add(@Valid @RequestBody Machine machine) {
        Machine newMachine = machineService.add(machine);
        return new ResponseEntity<>(newMachine, HttpStatus.CREATED);
    }

    // PUT /machines/{id}
    @PutMapping("/machines/{id}")
    public ResponseEntity<Machine> modify(@PathVariable long id, @RequestBody Machine machine)
            throws MachineNotFoundException {

        Machine updatedMachine = machineService.modify(id, machine);
        return ResponseEntity.ok(updatedMachine);
    }

    // DELETE /machines/{id}
    @DeleteMapping("/machines/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) throws MachineNotFoundException {
        machineService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
