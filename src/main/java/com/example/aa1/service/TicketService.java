package com.example.aa1.service;

import com.example.aa1.domain.Ticket;
import com.example.aa1.exception.TicketNotFoundException;
import com.example.aa1.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // CREATE
    public Ticket add(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // READ ALL
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    // READ BY ID
    public Ticket findById(Long id) throws TicketNotFoundException {
        return ticketRepository.findById(id)
                .orElseThrow(TicketNotFoundException::new);
    }

    // UPDATE
    public Ticket modify(Long id, Ticket ticket) throws TicketNotFoundException {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(TicketNotFoundException::new);

        ticket.setId(existingTicket.getId());
        return ticketRepository.save(ticket);
    }

    // DELETE
    public void delete(Long id) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(TicketNotFoundException::new);

        ticketRepository.delete(ticket);
    }
}
