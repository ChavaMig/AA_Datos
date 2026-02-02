package com.example.aa1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    // 400 - VALIDATION


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            errors.put(field, error.getDefaultMessage());
        });

        return new ResponseEntity<>(
                ErrorResponse.validationError(errors),
                HttpStatus.BAD_REQUEST
        );
    }


    // 404 - NOT FOUND


    @ExceptionHandler({
            ClinicNotFoundException.class,
            MachineNotFoundException.class,
            SparePartNotFoundException.class,
            TechnicianNotFoundException.class,
            TicketNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex) {

        return new ResponseEntity<>(
                ErrorResponse.notFound(ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }


    // 500 - INTERNAL ERROR


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalError(Exception ex) {

        return new ResponseEntity<>(
                ErrorResponse.generalError(
                        500,
                        "internal-error",
                        "Unexpected server error"
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
