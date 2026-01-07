package com.example.aa1.controller;

import com.example.aa1.domain.SparePart;
import com.example.aa1.exception.SparePartNotFoundException;
import com.example.aa1.service.SparePartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spare-parts")
public class SparePartController {

    private final SparePartService sparePartService;

    public SparePartController(SparePartService sparePartService) {
        this.sparePartService = sparePartService;
    }

    @GetMapping
    public ResponseEntity<List<SparePart>> getAll() {
        return ResponseEntity.ok(sparePartService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SparePart> getById(@PathVariable Long id) throws SparePartNotFoundException {
        return ResponseEntity.ok(sparePartService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SparePart> add(@Valid @RequestBody SparePart sparePart) {
        return new ResponseEntity<>(sparePartService.add(sparePart), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SparePart> modify(
            @PathVariable Long id,
            @Valid @RequestBody SparePart sparePart)
            throws SparePartNotFoundException {

        return ResponseEntity.ok(sparePartService.modify(id, sparePart));
    }

    // ✅ PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<SparePart> patch(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates)
            throws SparePartNotFoundException {

        return ResponseEntity.ok(sparePartService.patch(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws SparePartNotFoundException {
        sparePartService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(SparePartNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(SparePartNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
