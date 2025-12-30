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

    // GET
    @GetMapping
    public ResponseEntity<List<SparePart>> getAll() {
        return ResponseEntity.ok(sparePartService.findAll());
    }

    // GET
    @GetMapping("/{id}")
    public ResponseEntity<SparePart> getById(@PathVariable Long id) throws SparePartNotFoundException {
        return ResponseEntity.ok(sparePartService.findById(id));
    }

    // POST
    @PostMapping
    public ResponseEntity<SparePart> add(@Valid @RequestBody SparePart sparePart) {
        SparePart newSparePart = sparePartService.add(sparePart);
        return new ResponseEntity<>(newSparePart, HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<SparePart> modify(@PathVariable Long id,
                                            @Valid @RequestBody SparePart sparePart)
            throws SparePartNotFoundException {

        SparePart updatedSparePart = sparePartService.modify(id, sparePart);
        return ResponseEntity.ok(updatedSparePart);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws SparePartNotFoundException {
        sparePartService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ---- EXCEPTION HANDLER

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
