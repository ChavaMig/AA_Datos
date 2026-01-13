package com.example.aa1.controller;

import com.example.aa1.domain.Ticket;
import com.example.aa1.dto.TicketDto;
import com.example.aa1.dto.TicketOutDto;
import com.example.aa1.exception.ErrorResponse;
import com.example.aa1.exception.TicketNotFoundException;
import com.example.aa1.service.TicketService;
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
public class TicketController {

    @Autowired
    private TicketService ticketService;

    //  GET (CON FILTRADO)

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketOutDto>> getAll(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long machineId,
            @RequestParam(required = false) Long technicianId
    ) {
        return ResponseEntity.ok(
                ticketService.findWithFilters(status, machineId, technicianId)
        );
    }

    //  JPQL

    @GetMapping("/tickets/jpql/by-status")
    public ResponseEntity<List<TicketOutDto>> getByStatusJPQL(
            @RequestParam String status) {

        return ResponseEntity.ok(
                ticketService.findByStatusJPQL(status)
        );
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketDto> get(@PathVariable long id)
            throws TicketNotFoundException {

        TicketDto ticketDto = ticketService.findByIdDto(id);
        return ResponseEntity.ok(ticketDto);
    }

    //  POST

    @PostMapping("/tickets")
    public ResponseEntity<Ticket> addTicket(
            @Valid @RequestBody Ticket ticket) {

        Ticket newTicket = ticketService.add(ticket);
        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
    }

    //  PUT

    @PutMapping("/tickets/{id}")
    public ResponseEntity<Ticket> modifyTicket(
            @PathVariable long id,
            @RequestBody Ticket ticket)
            throws TicketNotFoundException {

        Ticket updatedTicket = ticketService.modify(id, ticket);
        return ResponseEntity.ok(updatedTicket);
    }

    //  PATCH

    @PatchMapping("/tickets/{id}")
    public ResponseEntity<Ticket> patchTicket(
            @PathVariable long id,
            @RequestBody Map<String, Object> updates)
            throws TicketNotFoundException {

        Ticket updatedTicket = ticketService.patch(id, updates);
        return ResponseEntity.ok(updatedTicket);
    }

    //  DELETE

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable long id)
            throws TicketNotFoundException {

        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //  EXCEPTIONS

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(TicketNotFoundException tnfe) {
        ErrorResponse errorResponse =
                ErrorResponse.notFound("The ticket does not exist");
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
