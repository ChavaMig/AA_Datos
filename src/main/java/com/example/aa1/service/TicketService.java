package com.example.aa1.service;

import com.example.aa1.domain.Ticket;
import com.example.aa1.dto.TicketDto;
import com.example.aa1.dto.TicketOutDto;
import com.example.aa1.exception.TicketNotFoundException;
import com.example.aa1.repository.TicketRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ModelMapper modelMapper;

    // ===================== CRUD =====================

    public Ticket add(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void delete(long id) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        ticketRepository.delete(ticket);
    }

    // ===================== GET CON FILTRADO =====================

    public List<TicketOutDto> findWithFilters(
            String status,
            Long machineId,
            Long technicianId
    ) {

        List<Ticket> tickets = ticketRepository.findAll();

        List<Ticket> filtered = tickets.stream()
                .filter(t -> status == null ||
                        t.getStatus().equalsIgnoreCase(status))
                .filter(t -> machineId == null ||
                        (t.getMachine() != null &&
                                t.getMachine().getId().equals(machineId)))
                .filter(t -> technicianId == null ||
                        (t.getTechnician() != null &&
                                t.getTechnician().getId().equals(technicianId)))
                .toList();

        return modelMapper.map(
                filtered,
                new TypeToken<List<TicketOutDto>>() {}.getType()
        );
    }

    public TicketDto findByIdDto(long id) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        return modelMapper.map(ticket, TicketDto.class);
    }

    public Ticket modify(long id, Ticket ticket)
            throws TicketNotFoundException {

        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        modelMapper.map(ticket, existingTicket);
        existingTicket.setId(id);

        return ticketRepository.save(existingTicket);
    }

    // ===================== PATCH =====================

    public Ticket patch(long id, Map<String, Object> updates)
            throws TicketNotFoundException {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Ticket.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, ticket, value);
            }
        });

        return ticketRepository.save(ticket);
    }
}
