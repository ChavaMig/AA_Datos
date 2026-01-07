package com.example.aa1.controller;

import com.example.aa1.domain.Machine;
import com.example.aa1.exception.MachineNotFoundException;
import com.example.aa1.service.MachineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
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

@WebMvcTest(MachineController.class)
@Import(com.example.aa1.config.AppConfig.class)
class MachineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MachineService machineService;

    @Autowired
    private ObjectMapper objectMapper;

    // -------------------------
    // 200 OK - GET /machines
    // -------------------------
    @Test
    void getAllMachines_returns200() throws Exception {
        when(machineService.findAll())
                .thenReturn(List.of(new Machine(), new Machine()));

        mockMvc.perform(get("/machines"))
                .andExpect(status().isOk());
    }

    // -------------------------
    // 200 OK - GET /machines/{id}
    // -------------------------
    @Test
    void getMachine_existingId_returns200() throws Exception {
        Machine machine = Machine.builder()
                .id(1L)
                .serialNumber("SN123")
                .build();

        when(machineService.findById(1L)).thenReturn(machine);

        mockMvc.perform(get("/machines/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value("SN123"));
    }

    // -------------------------
    // 404 NOT FOUND - GET /machines/{id}
    // -------------------------
    @Test
    void getMachine_nonExistingId_returns404() throws Exception {
        when(machineService.findById(99L))
                .thenThrow(new MachineNotFoundException("Machine not found"));

        mockMvc.perform(get("/machines/99"))
                .andExpect(status().isNotFound());
    }



    // -------------------------
    // 400 BAD REQUEST - POST /machines
    // -------------------------
    @Test
    void addMachine_invalidBody_returns400() throws Exception {
        // serialNumber es obligatorio → lo omitimos
        Machine machine = Machine.builder()
                .model("Model X")
                .manufacturer("ACME")
                .build();

        mockMvc.perform(post("/machines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(machine)))
                .andExpect(status().isBadRequest());
    }

    // -------------------------
    // 201 CREATED - POST /machines
    // -------------------------
    @Test
    void addMachine_validBody_returns201() throws Exception {
        Machine machine = Machine.builder()
                .serialNumber("SN123")
                .model("Model X")
                .manufacturer("ACME")
                .build();

        when(machineService.add(any(Machine.class)))
                .thenReturn(machine);

        mockMvc.perform(post("/machines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(machine)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serialNumber").value("SN123"));
    }

    // -------------------------
    // 200 OK - PATCH /machines/{id}
    // -------------------------
    @Test
    void patchMachine_existingId_returns200() throws Exception {
        Machine machine = Machine.builder()
                .id(1L)
                .serialNumber("NEW")
                .build();

        when(machineService.patch(anyLong(), any(Map.class)))
                .thenReturn(machine);

        mockMvc.perform(patch("/machines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                Map.of("serialNumber", "NEW")
                        )))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value("NEW"));
    }

    // -------------------------
    // 404 NOT FOUND - PATCH /machines/{id}
    // -------------------------
    @Test
    void patchMachine_nonExistingId_returns404() throws Exception {
        when(machineService.patch(anyLong(), any(Map.class)))
                .thenThrow(new MachineNotFoundException("Machine not found"));

        mockMvc.perform(patch("/machines/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                Map.of("model", "X")
                        )))
                .andExpect(status().isNotFound());
    }

    // -------------------------
    // 404 NOT FOUND - DELETE /machines/{id}
    // -------------------------
    @Test
    void deleteMachine_nonExistingId_returns404() throws Exception {
        doThrow(new MachineNotFoundException("Machine not found"))
                .when(machineService).delete(99L);


        mockMvc.perform(delete("/machines/99"))
                .andExpect(status().isNotFound());
    }
}
