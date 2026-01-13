package com.example.aa1.controller;

import com.example.aa1.domain.Technician;
import com.example.aa1.exception.TechnicianNotFoundException;
import com.example.aa1.service.TechnicianService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/technicians")
public class TechnicianController {

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    // GET (CON FILTRADO )

    @GetMapping
    public ResponseEntity<List<Technician>> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Boolean active
    ) {
        return ResponseEntity.ok(
                technicianService.findWithFilters(name, email, active)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technician> get(@PathVariable long id)
            throws TechnicianNotFoundException {

        return ResponseEntity.ok(technicianService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Technician> add(@Valid @RequestBody Technician technician) {
        return new ResponseEntity<>(technicianService.add(technician), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Technician> modify(
            @PathVariable long id,
            @RequestBody Technician technician)
            throws TechnicianNotFoundException {

        return ResponseEntity.ok(technicianService.modify(id, technician));
    }

    //  PATCH

    @PatchMapping("/{id}")
    public ResponseEntity<Technician> patch(
            @PathVariable long id,
            @RequestBody Map<String, Object> updates)
            throws TechnicianNotFoundException {

        return ResponseEntity.ok(technicianService.patch(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id)
            throws TechnicianNotFoundException {

        technicianService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(TechnicianNotFoundException.class)
    public ResponseEntity<String> handleTechnicianNotFound(
            TechnicianNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //  JPQL

    @GetMapping("/by-active/{active}")
    public ResponseEntity<List<Technician>> getByActive(
            @PathVariable boolean active) {

        return ResponseEntity.ok(
                technicianService.findByActive(active)
        );
    }
}
