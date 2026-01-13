package com.example.aa1.controller;

import com.example.aa1.domain.Clinic;
import com.example.aa1.dto.ClinicDto;
import com.example.aa1.dto.ClinicOutDto;
import com.example.aa1.exception.ClinicNotFoundException;
import com.example.aa1.exception.ErrorResponse;
import com.example.aa1.service.ClinicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    //  GET (CON FILTRADO )

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

    @GetMapping("/clinics/{id}")
    public ResponseEntity<ClinicDto> get(@PathVariable long id)
            throws ClinicNotFoundException {

        ClinicDto clinicDto = clinicService.findById(id);
        return ResponseEntity.ok(clinicDto);
    }

    @PostMapping("/clinics")
    public ResponseEntity<Clinic> addClinic(@Valid @RequestBody Clinic clinic) {
        Clinic newClinic = clinicService.add(clinic);
        return new ResponseEntity<>(newClinic, HttpStatus.CREATED);
    }

    @PutMapping("/clinics/{id}")
    public ResponseEntity<Clinic> modifyClinic(
            @PathVariable long id,
            @RequestBody Clinic clinic)
            throws ClinicNotFoundException {

        Clinic newClinic = clinicService.modify(id, clinic);
        return ResponseEntity.ok(newClinic);
    }

    //  PATCH

    @PatchMapping("/clinics/{id}")
    public ResponseEntity<Clinic> patchClinic(
            @PathVariable long id,
            @RequestBody Map<String, Object> updates)
            throws ClinicNotFoundException {

        Clinic updatedClinic = clinicService.patch(id, updates);
        return ResponseEntity.ok(updatedClinic);
    }

    @DeleteMapping("/clinics/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable long id)
            throws ClinicNotFoundException {

        clinicService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //  EXCEPTIONS

    @ExceptionHandler(ClinicNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(
            ClinicNotFoundException cnfe) {

        ErrorResponse errorResponse =
                ErrorResponse.notFound("The clinic does not exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(
            MethodArgumentNotValidException manve) {

        Map<String, String> errors = new HashMap<>();

        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorResponse errorResponse =
                ErrorResponse.validationError(errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
