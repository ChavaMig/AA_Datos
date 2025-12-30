package com.example.aa1.controller;

import com.example.aa1.domain.Ticket;
import com.example.aa1.exception.TicketNotFoundException;
import com.example.aa1.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // GET /tickets
    @GetMapping
    public ResponseEntity<List<Ticket>> getAll() {
        return ResponseEntity.ok(ticketService.findAll());
    }

    // GET /tickets/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable Long id) throws TicketNotFoundException {
        Ticket ticket = ticketService.findById(id);
        return ResponseEntity.ok(ticket);
    }

    // POST /tickets
    @PostMapping
    public ResponseEntity<Ticket> add(@Valid @RequestBody Ticket ticket) {
        Ticket newTicket = ticketService.add(ticket);
        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
    }

    // PUT /tickets/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> modify(@PathVariable Long id, @RequestBody Ticket ticket)
            throws TicketNotFoundException {
        Ticket updatedTicket = ticketService.modify(id, ticket);
        return ResponseEntity.ok(updatedTicket);
    }

    // DELETE /tickets/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws TicketNotFoundException {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
