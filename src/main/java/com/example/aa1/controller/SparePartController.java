package com.example.aa1.controller;

import com.example.aa1.domain.SparePart;
import com.example.aa1.exception.SparePartNotFoundException;
import com.example.aa1.service.SparePartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spare-parts")
public class SparePartController {

    private final SparePartService sparePartService;

    public SparePartController(SparePartService sparePartService) {
        this.sparePartService = sparePartService;
    }


    // GET (CON FILTRADO)


    @GetMapping
    public ResponseEntity<List<SparePart>> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) Double price
    ) {
        return ResponseEntity.ok(
                sparePartService.findWithFilters(name, manufacturer, price)
        );
    }


    // GET BY ID


    @GetMapping("/{id}")
    public ResponseEntity<SparePart> getById(@PathVariable Long id)
            throws SparePartNotFoundException {

        return ResponseEntity.ok(
                sparePartService.findById(id)
        );
    }


    // POST


    @PostMapping
    public ResponseEntity<SparePart> add(
            @Valid @RequestBody SparePart sparePart) {

        return new ResponseEntity<>(
                sparePartService.add(sparePart),
                HttpStatus.CREATED
        );
    }


    // PUT


    @PutMapping("/{id}")
    public ResponseEntity<SparePart> modify(
            @PathVariable Long id,
            @Valid @RequestBody SparePart sparePart)
            throws SparePartNotFoundException {

        return ResponseEntity.ok(
                sparePartService.modify(id, sparePart)
        );
    }


    // PATCH


    @PatchMapping("/{id}")
    public ResponseEntity<SparePart> patch(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates)
            throws SparePartNotFoundException {

        return ResponseEntity.ok(
                sparePartService.patch(id, updates)
        );
    }


    // DELETE


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
            throws SparePartNotFoundException {

        sparePartService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
