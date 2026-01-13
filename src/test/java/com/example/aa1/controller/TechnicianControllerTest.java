package com.example.aa1.controller;

import com.example.aa1.domain.Technician;
import com.example.aa1.exception.TechnicianNotFoundException;
import com.example.aa1.service.TechnicianService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TechnicianController.class)
class TechnicianControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TechnicianService technicianService;

    @Autowired
    private ObjectMapper objectMapper;

    //  GET ALL

    @Test
    void getAll_returns200() throws Exception {
        when(technicianService.findAll())
                .thenReturn(List.of(new Technician(), new Technician()));

        mockMvc.perform(get("/technicians"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    //  GET technicians/ {id}

    @Test
    void get_existingId_returns200() throws Exception {
        Technician tech = Technician.builder().id(1L).name("Ana").build();

        when(technicianService.findById(1L)).thenReturn(tech);

        mockMvc.perform(get("/technicians/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ana"));
    }

    @Test
    void get_nonExistingId_returns404() throws Exception {
        when(technicianService.findById(1L))
                .thenThrow(new TechnicianNotFoundException("Technician not found"));

        mockMvc.perform(get("/technicians/1"))
                .andExpect(status().isNotFound());
    }

    //  POST

    @Test
    void add_validTechnician_returns201() throws Exception {
        Technician tech = Technician.builder()
                .name("Ana")
                .surname("Lopez")
                .email("ana@test.com")
                .active(true)
                .build();

        when(technicianService.add(any(Technician.class)))
                .thenReturn(tech);

        mockMvc.perform(post("/technicians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tech)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Ana"));
    }

    @Test
    void add_invalidTechnician_returns400() throws Exception {
        Technician invalid = new Technician(); // faltan campos @Valid

        mockMvc.perform(post("/technicians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    //  PUT

    @Test
    void modify_existingTechnician_returns200() throws Exception {
        Technician tech = Technician.builder().name("Ana").build();

        when(technicianService.modify(eq(1L), any(Technician.class)))
                .thenReturn(tech);

        mockMvc.perform(put("/technicians/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tech)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ana"));
    }

    @Test
    void modify_nonExistingTechnician_returns404() throws Exception {
        when(technicianService.modify(eq(1L), any(Technician.class)))
                .thenThrow(new TechnicianNotFoundException("Technician not found"));

        mockMvc.perform(put("/technicians/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isNotFound());
    }

    //  PATCH

    @Test
    void patch_existingTechnician_returns200() throws Exception {
        Technician tech = Technician.builder().name("Maria").build();

        when(technicianService.patch(eq(1L), anyMap()))
                .thenReturn(tech);

        mockMvc.perform(patch("/technicians/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                Map.of("name", "Maria"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Maria"));
    }

    @Test
    void patch_nonExistingTechnician_returns404() throws Exception {
        when(technicianService.patch(eq(1L), anyMap()))
                .thenThrow(new TechnicianNotFoundException("Technician not found"));

        mockMvc.perform(patch("/technicians/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isNotFound());
    }

    //  DELETE

    @Test
    void delete_existingTechnician_returns204() throws Exception {
        doNothing().when(technicianService).delete(1L);

        mockMvc.perform(delete("/technicians/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_nonExistingTechnician_returns404() throws Exception {
        doThrow(new TechnicianNotFoundException("Technician not found"))
                .when(technicianService).delete(1L);

        mockMvc.perform(delete("/technicians/1"))
                .andExpect(status().isNotFound());
    }
}
