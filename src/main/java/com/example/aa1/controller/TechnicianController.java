package com.example.aa1.controller;

import com.example.aa1.domain.Technician;
import com.example.aa1.exception.TechnicianNotFoundException;
import com.example.aa1.service.TechnicianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technicians")
public class TechnicianController {

    @Autowired
    private TechnicianService technicianService;

    // GET /technicians
    @GetMapping
    public ResponseEntity<List<Technician>> getAll() {
        List<Technician> technicians = technicianService.findAll();
        return ResponseEntity.ok(technicians);
    }

    // GET /technicians/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Technician> get(@PathVariable long id) throws TechnicianNotFoundException {
        Technician technician = technicianService.findById(id);
        return ResponseEntity.ok(technician);
    }

    // POST /technicians
    @PostMapping
    public ResponseEntity<Technician> add(@Valid @RequestBody Technician technician) {
        Technician newTechnician = technicianService.add(technician);
        return new ResponseEntity<>(newTechnician, HttpStatus.CREATED);
    }

    // PUT /technicians/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Technician> modify(@PathVariable long id,
                                             @RequestBody Technician technician)
            throws TechnicianNotFoundException {
        Technician updatedTechnician = technicianService.modify(id, technician);
        return ResponseEntity.ok(updatedTechnician);
    }

    // DELETE /technicians/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id)
            throws TechnicianNotFoundException {
        technicianService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Manejo de excepción
    @ExceptionHandler(TechnicianNotFoundException.class)
    public ResponseEntity<String> handleTechnicianNotFound(TechnicianNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
