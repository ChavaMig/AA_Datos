package com.example.aa1.controller;

import com.example.aa1.domain.Clinic;
import com.example.aa1.dto.ClinicDto;
import com.example.aa1.exception.ClinicNotFoundException;
import com.example.aa1.service.ClinicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClinicController.class)
@Import(com.example.aa1.config.AppConfig.class)
class ClinicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClinicService clinicService;

    @Autowired
    private ObjectMapper objectMapper;


    // 200 OK - GET /clinics/id

    @Test
    void getClinic_existingId_returns200() throws Exception {

        ClinicDto dto = new ClinicDto();
        dto.setId(1L);
        dto.setName("Clinica Central");
        dto.setAddress("Calle Mayor");

        when(clinicService.findById(1L)).thenReturn(dto);

        mockMvc.perform(get("/clinics/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Clinica Central"));
    }


    // 404 NOT FOUND - GET /clinics/id

    @Test
    void getClinic_nonExistingId_returns404() throws Exception {

        when(clinicService.findById(99L))
                .thenThrow(new ClinicNotFoundException("Clinica no encontrada"));

        mockMvc.perform(get("/clinics/99"))
                .andExpect(status().isNotFound());
    }


    // 400 BAD REQUEST

    @Test
    void addClinic_invalidBody_returns400() throws Exception {

        // name es obligatorio
        Clinic clinic = Clinic.builder()
                .address("Calle Mayor")
                .build();

        mockMvc.perform(post("/clinics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clinic)))
                .andExpect(status().isBadRequest());
    }


    // 200 OK - POST

    @Test
    void addClinic_validBody_returns201() throws Exception {

        Clinic clinic = Clinic.builder()
                .name("Clinica Central")
                .address("Calle Mayor")
                .build();

        when(clinicService.add(any(Clinic.class)))
                .thenReturn(clinic);

        mockMvc.perform(post("/clinics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clinic)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Clinica Central"));
    }
}
