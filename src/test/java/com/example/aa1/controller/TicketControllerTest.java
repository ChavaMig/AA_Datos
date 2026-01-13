package com.example.aa1.controller;

import com.example.aa1.domain.Ticket;
import com.example.aa1.dto.TicketDto;
import com.example.aa1.dto.TicketOutDto;
import com.example.aa1.exception.TicketNotFoundException;
import com.example.aa1.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Autowired
    private ObjectMapper objectMapper;

    //  GET

    @Test
    void getAll_returns200() throws Exception {
        when(ticketService.findAllOut())
                .thenReturn(List.of(new TicketOutDto()));

        mockMvc.perform(get("/tickets"))
                .andExpect(status().isOk());
    }

    @Test
    void getById_existing_returns200() throws Exception {
        TicketDto dto = new TicketDto();
        dto.setDescription("Test");
        dto.setStatus("OPEN");
        dto.setOpenDate(LocalDate.now());
        dto.setMachineId(1L);
        dto.setTechnicianId(1L);

        when(ticketService.findByIdDto(1L)).thenReturn(dto);

        mockMvc.perform(get("/tickets/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getById_notFound_returns404() throws Exception {
        when(ticketService.findByIdDto(1L))
                .thenThrow(new TicketNotFoundException("Ticket not found"));

        mockMvc.perform(get("/tickets/1"))
                .andExpect(status().isNotFound());
    }

    // ===================== GET BY TECHNICIAN =====================

    @Test
    void getByTechnician_returns200() throws Exception {
        when(ticketService.findByTechnicianOut(1L))
                .thenReturn(List.of(new TicketOutDto()));

        mockMvc.perform(get("/tickets/by-technician/1"))
                .andExpect(status().isOk());
    }

    // ===================== POST =====================

    @Test
    void addTicket_valid_returns201() throws Exception {
        Ticket ticket = buildTicket();

        when(ticketService.add(any(Ticket.class)))
                .thenReturn(ticket);

        mockMvc.perform(post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isCreated());
    }

    @Test
    void addTicket_invalid_returns400() throws Exception {
        Ticket ticket = new Ticket(); // falta description, status, openDate

        mockMvc.perform(post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isBadRequest());
    }

    // ===================== PUT =====================

    @Test
    void modifyTicket_existing_returns200() throws Exception {
        Ticket ticket = buildTicket();

        when(ticketService.modify(eq(1L), any(Ticket.class)))
                .thenReturn(ticket);

        mockMvc.perform(put("/tickets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk());
    }

    @Test
    void modifyTicket_notFound_returns404() throws Exception {
        Ticket ticket = buildTicket();

        when(ticketService.modify(eq(1L), any(Ticket.class)))
                .thenThrow(new TicketNotFoundException("Ticket not found"));

        mockMvc.perform(put("/tickets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isNotFound());
    }

    // ===================== PATCH =====================

    @Test
    void patchTicket_existing_returns200() throws Exception {
        Ticket ticket = buildTicket();

        when(ticketService.patch(eq(1L), anyMap()))
                .thenReturn(ticket);

        mockMvc.perform(patch("/tickets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                Map.of("status", "CLOSED"))))
                .andExpect(status().isOk());
    }

    @Test
    void patchTicket_notFound_returns404() throws Exception {
        when(ticketService.patch(eq(1L), anyMap()))
                .thenThrow(new TicketNotFoundException("Ticket not found"));

        mockMvc.perform(patch("/tickets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                Map.of("status", "CLOSED"))))
                .andExpect(status().isNotFound());
    }

    // ===================== DELETE =====================

    @Test
    void deleteTicket_existing_returns204() throws Exception {
        doNothing().when(ticketService).delete(1L);

        mockMvc.perform(delete("/tickets/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteTicket_notFound_returns404() throws Exception {
        doThrow(new TicketNotFoundException("Ticket not found"))
                .when(ticketService).delete(1L);

        mockMvc.perform(delete("/tickets/1"))
                .andExpect(status().isNotFound());
    }

    // ===================== HELPER =====================

    private Ticket buildTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setDescription("Test ticket");
        ticket.setStatus("OPEN");
        ticket.setOpenDate(LocalDate.now());
        return ticket;
    }
}
