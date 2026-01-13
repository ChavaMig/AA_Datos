package com.example.aa1.controller;

import com.example.aa1.domain.SparePart;
import com.example.aa1.exception.SparePartNotFoundException;
import com.example.aa1.service.SparePartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SparePartController.class)
class SparePartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SparePartService sparePartService;

    @Autowired
    private ObjectMapper objectMapper;


    // 200 OK - GET /spare-parts

    @Test
    void getAllSpareParts_returns200() throws Exception {
        when(sparePartService.findAll())
                .thenReturn(List.of(new SparePart(), new SparePart()));

        mockMvc.perform(get("/spare-parts"))
                .andExpect(status().isOk());
    }


    // 200 OK - GET /spare-parts/{id}

    @Test
    void getSparePart_existingId_returns200() throws Exception {
        SparePart sparePart = SparePart.builder()
                .id(1L)
                .name("Filtro")
                .serialNumber("SP123")
                .manufacturer("ACME")
                .price(10.0)
                .stock(5)
                .build();

        when(sparePartService.findById(1L))
                .thenReturn(sparePart);

        mockMvc.perform(get("/spare-parts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Filtro"));
    }


    // 404 NOT FOUND - GET /spare-parts/{id}

    @Test
    void getSparePart_nonExistingId_returns404() throws Exception {
        when(sparePartService.findById(99L))
                .thenThrow(new SparePartNotFoundException("Spare part not found"));

        mockMvc.perform(get("/spare-parts/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists());
    }


    // 201 CREATED - POST /spare-parts

    @Test
    void addSparePart_validBody_returns201() throws Exception {
        SparePart sparePart = SparePart.builder()
                .name("Filtro")
                .serialNumber("SP123")
                .manufacturer("ACME")
                .price(10.0)
                .stock(5)
                .build();

        when(sparePartService.add(any(SparePart.class)))
                .thenReturn(sparePart);

        mockMvc.perform(post("/spare-parts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sparePart)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Filtro"));
    }


    // 400 BAD REQUEST - POST /spare-parts

    @Test
    void addSparePart_invalidBody_returns400() throws Exception {
        // name es obligatorio → lo omitimos
        SparePart sparePart = SparePart.builder()
                .serialNumber("SP123")
                .manufacturer("ACME")
                .price(10.0)
                .stock(5)
                .build();

        mockMvc.perform(post("/spare-parts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sparePart)))
                .andExpect(status().isBadRequest());
    }


    // 200 OK - PATCH /spare-parts/{id}

    @Test
    void patchSparePart_existingId_returns200() throws Exception {
        SparePart sparePart = SparePart.builder()
                .id(1L)
                .name("Nuevo")
                .build();

        when(sparePartService.patch(anyLong(), any(Map.class)))
                .thenReturn(sparePart);

        mockMvc.perform(patch("/spare-parts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                Map.of("name", "Nuevo")
                        )))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nuevo"));
    }


    // 404 NOT FOUND - PATCH /spare-parts/{id}

    @Test
    void patchSparePart_nonExistingId_returns404() throws Exception {
        when(sparePartService.patch(anyLong(), any(Map.class)))
                .thenThrow(new SparePartNotFoundException("Spare part not found"));

        mockMvc.perform(patch("/spare-parts/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                Map.of("name", "X")
                        )))
                .andExpect(status().isNotFound());
    }


    // 404 NOT FOUND - DELETE /spare-parts/{id}

    @Test
    void deleteSparePart_nonExistingId_returns404() throws Exception {
        doThrow(new SparePartNotFoundException("Spare part not found"))
                .when(sparePartService).delete(99L);

        mockMvc.perform(delete("/spare-parts/99"))
                .andExpect(status().isNotFound());
    }
}
