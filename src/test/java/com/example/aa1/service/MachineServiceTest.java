package com.example.aa1.service;

import com.example.aa1.domain.Machine;
import com.example.aa1.exception.MachineNotFoundException;
import com.example.aa1.repository.MachineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MachineServiceTest {

    @Mock
    private MachineRepository machineRepository;

    @InjectMocks
    private MachineService machineService;

    // -------------------------
    // CREATE
    // -------------------------
    @Test
    void add_validMachine_savesMachine() {
        Machine machine = Machine.builder()
                .serialNumber("SN123")
                .model("Model X")
                .manufacturer("ACME")
                .build();

        when(machineRepository.save(machine)).thenReturn(machine);

        Machine result = machineService.add(machine);

        assertNotNull(result);
        assertEquals("SN123", result.getSerialNumber());
        verify(machineRepository).save(machine);
    }

    // -------------------------
    // READ ALL
    // -------------------------
    @Test
    void findAll_returnsMachineList() {
        List<Machine> machines = List.of(
                Machine.builder().id(1L).serialNumber("A").build(),
                Machine.builder().id(2L).serialNumber("B").build()
        );

        when(machineRepository.findAll()).thenReturn(machines);

        List<Machine> result = machineService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(machineRepository).findAll();
    }

    // -------------------------
    // READ BY ID (OK)
    // -------------------------
    @Test
    void findById_existingMachine_returnsMachine() throws Exception {
        Machine machine = Machine.builder()
                .id(1L)
                .serialNumber("SN123")
                .model("Model X")
                .manufacturer("ACME")
                .build();

        when(machineRepository.findById(1L))
                .thenReturn(Optional.of(machine));

        Machine result = machineService.findById(1L);

        assertNotNull(result);
        assertEquals("SN123", result.getSerialNumber());
        verify(machineRepository).findById(1L);
    }

    // -------------------------
    // READ BY ID (NOT FOUND)
    // -------------------------
    @Test
    void findById_nonExistingMachine_throwsException() {
        when(machineRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(MachineNotFoundException.class,
                () -> machineService.findById(99L));
    }

    // -------------------------
    // UPDATE (PUT) OK
    // -------------------------
    @Test
    void modify_existingMachine_updatesMachine() throws Exception {
        Machine existing = Machine.builder()
                .id(1L)
                .serialNumber("OLD")
                .model("Old Model")
                .manufacturer("Old")
                .build();

        Machine updated = Machine.builder()
                .serialNumber("NEW")
                .model("New Model")
                .manufacturer("New")
                .build();

        when(machineRepository.findById(1L))
                .thenReturn(Optional.of(existing));
        when(machineRepository.save(existing))
                .thenReturn(existing);

        Machine result = machineService.modify(1L, updated);

        assertEquals("NEW", result.getSerialNumber());
        assertEquals("New Model", result.getModel());
        assertEquals("New", result.getManufacturer());
        verify(machineRepository).save(existing);
    }

    // -------------------------
    // UPDATE (PUT) NOT FOUND
    // -------------------------
    @Test
    void modify_nonExistingMachine_throwsException() {
        when(machineRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(MachineNotFoundException.class,
                () -> machineService.modify(99L, new Machine()));
    }

    // -------------------------
    // UPDATE (PATCH) OK
    // -------------------------
    @Test
    void patch_existingMachine_updatesFields() throws Exception {
        Machine machine = Machine.builder()
                .id(1L)
                .serialNumber("OLD")
                .model("Old Model")
                .manufacturer("Old")
                .build();

        Map<String, Object> updates = Map.of(
                "serialNumber", "NEW",
                "model", "New Model"
        );

        when(machineRepository.findById(1L))
                .thenReturn(Optional.of(machine));
        when(machineRepository.save(machine))
                .thenReturn(machine);

        Machine result = machineService.patch(1L, updates);

        assertEquals("NEW", result.getSerialNumber());
        assertEquals("New Model", result.getModel());
        verify(machineRepository).save(machine);
    }

    // -------------------------
    // UPDATE (PATCH) NOT FOUND
    // -------------------------
    @Test
    void patch_nonExistingMachine_throwsException() {
        when(machineRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(MachineNotFoundException.class,
                () -> machineService.patch(99L, Map.of("model", "X")));
    }

    // -------------------------
    // DELETE (OK)
    // -------------------------
    @Test
    void delete_existingMachine_deletesMachine() throws Exception {
        Machine machine = Machine.builder()
                .id(1L)
                .serialNumber("SN123")
                .build();

        when(machineRepository.findById(1L))
                .thenReturn(Optional.of(machine));

        machineService.delete(1L);

        verify(machineRepository).delete(machine);
    }

    // -------------------------
    // DELETE (NOT FOUND)
    // -------------------------
    @Test
    void delete_nonExistingMachine_throwsException() {
        when(machineRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(MachineNotFoundException.class,
                () -> machineService.delete(99L));
    }
}
