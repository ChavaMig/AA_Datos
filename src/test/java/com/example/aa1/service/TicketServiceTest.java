package com.example.aa1.service;

import com.example.aa1.domain.Ticket;
import com.example.aa1.dto.TicketDto;
import com.example.aa1.dto.TicketOutDto;
import com.example.aa1.exception.TicketNotFoundException;
import com.example.aa1.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    // 👉 REAL, NO MOCK
    private ModelMapper modelMapper;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        ticketService = new TicketService();
        ticketService.ticketRepository = ticketRepository;
        ticketService.modelMapper = modelMapper;
    }

    // ADD

    @Test
    void add_savesTicket() {
        Ticket ticket = buildTicket();

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.add(ticket);

        assertNotNull(result);
        verify(ticketRepository).save(ticket);
    }

    // FIND ALL

    @Test
    void findAllOut_returnsDtos() {
        when(ticketRepository.findAll())
                .thenReturn(List.of(buildTicket()));

        List<TicketOutDto> result = ticketService.findAllOut();

        assertEquals(1, result.size());
    }

    //  FIND BY ID

    @Test
    void findByIdDto_existing_returnsDto() throws Exception {
        when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(buildTicket()));

        TicketDto dto = ticketService.findByIdDto(1L);

        assertEquals("Test ticket", dto.getDescription());
    }

    @Test
    void findByIdDto_notFound_throwsException() {
        when(ticketRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(TicketNotFoundException.class,
                () -> ticketService.findByIdDto(1L));
    }

    //  MODIFY

    @Test
    void modify_existing_updatesTicket() throws Exception {
        Ticket existing = buildTicket();
        Ticket updated = buildTicket();
        updated.setStatus("CLOSED");

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(existing));
        when(ticketRepository.save(any(Ticket.class)))
                .thenReturn(updated);

        Ticket result = ticketService.modify(1L, updated);

        assertEquals("CLOSED", result.getStatus());
    }

    //  PATCH

    @Test
    void patch_existing_updatesField() throws Exception {
        Ticket ticket = buildTicket();

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class)))
                .thenReturn(ticket);

        Ticket result = ticketService.patch(
                1L, Map.of("status", "CLOSED"));

        assertEquals("CLOSED", result.getStatus());
    }

    // DELETE

    @Test
    void delete_existing_deletesTicket() throws Exception {
        Ticket ticket = buildTicket();

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(ticket));

        ticketService.delete(1L);

        verify(ticketRepository).delete(ticket);
    }

    @Test
    void delete_notFound_throwsException() {
        when(ticketRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(TicketNotFoundException.class,
                () -> ticketService.delete(1L));
    }

    // EXTRA

    @Test
    void findByTechnicianOut_returnsDtos() {
        when(ticketRepository.findByTechnicianId(1L))
                .thenReturn(List.of(buildTicket()));

        List<TicketOutDto> result =
                ticketService.findByTechnicianOut(1L);

        assertEquals(1, result.size());
    }


    private Ticket buildTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setDescription("Test ticket");
        ticket.setStatus("OPEN");
        ticket.setOpenDate(LocalDate.now());
        return ticket;
    }
}
