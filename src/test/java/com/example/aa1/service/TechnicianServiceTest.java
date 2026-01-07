package com.example.aa1.service;

import com.example.aa1.domain.Technician;
import com.example.aa1.exception.TechnicianNotFoundException;
import com.example.aa1.repository.TechnicianRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechnicianServiceTest {

    @Mock
    private TechnicianRepository technicianRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TechnicianService technicianService;

    // ---------- ADD ----------

    @Test
    void add_returnsSavedTechnician() {
        Technician tech = Technician.builder()
                .name("Ana")
                .surname("Lopez")
                .email("ana@test.com")
                .active(true)
                .build();

        when(technicianRepository.save(tech)).thenReturn(tech);

        Technician result = technicianService.add(tech);

        assertNotNull(result);
        assertEquals("Ana", result.getName());
        verify(technicianRepository).save(tech);
    }

    // ---------- FIND ALL ----------

    @Test
    void findAll_returnsTechnicianList() {
        List<Technician> technicians = List.of(
                Technician.builder().id(1L).name("Ana").build(),
                Technician.builder().id(2L).name("Luis").build()
        );

        when(technicianRepository.findAll()).thenReturn(technicians);

        List<Technician> result = technicianService.findAll();

        assertEquals(2, result.size());
        verify(technicianRepository).findAll();
    }

    // ---------- FIND BY ID ----------

    @Test
    void findById_existingId_returnsTechnician() throws TechnicianNotFoundException {
        Technician tech = Technician.builder()
                .id(1L)
                .name("Ana")
                .build();

        when(technicianRepository.findById(1L))
                .thenReturn(Optional.of(tech));

        Technician result = technicianService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void findById_nonExistingId_throwsException() {
        when(technicianRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(TechnicianNotFoundException.class,
                () -> technicianService.findById(1L));
    }

    // ---------- MODIFY ----------

    @Test
    void modify_existingTechnician_updatesTechnician() throws TechnicianNotFoundException {
        Technician existing = Technician.builder()
                .id(1L)
                .name("Old")
                .surname("Name")
                .email("old@test.com")
                .build();

        Technician updated = Technician.builder()
                .name("New")
                .surname("Surname")
                .email("new@test.com")
                .build();

        when(technicianRepository.findById(1L))
                .thenReturn(Optional.of(existing));
        when(technicianRepository.save(existing))
                .thenReturn(existing);

        doNothing().when(modelMapper).map(updated, existing);

        Technician result = technicianService.modify(1L, updated);

        assertEquals(1L, result.getId());
        verify(modelMapper).map(updated, existing);
        verify(technicianRepository).save(existing);
    }

    @Test
    void modify_nonExistingTechnician_throwsException() {
        when(technicianRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(TechnicianNotFoundException.class,
                () -> technicianService.modify(1L, new Technician()));
    }

    // ---------- PATCH ----------

    @Test
    void patch_existingTechnician_updatesFields() throws TechnicianNotFoundException {
        Technician tech = Technician.builder()
                .id(1L)
                .name("Ana")
                .surname("Lopez")
                .email("ana@test.com")
                .active(true)
                .build();

        Map<String, Object> updates = Map.of(
                "name", "Maria",
                "active", false
        );

        when(technicianRepository.findById(1L))
                .thenReturn(Optional.of(tech));
        when(technicianRepository.save(tech))
                .thenReturn(tech);

        Technician result = technicianService.patch(1L, updates);

        assertEquals("Maria", result.getName());
        assertFalse(result.isActive());
        verify(technicianRepository).save(tech);
    }

    @Test
    void patch_nonExistingTechnician_throwsException() {
        when(technicianRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(TechnicianNotFoundException.class,
                () -> technicianService.patch(1L, Map.of("name", "Test")));
    }

    // ---------- DELETE ----------

    @Test
    void delete_existingTechnician_deletesTechnician() throws TechnicianNotFoundException {
        Technician tech = Technician.builder().id(1L).build();

        when(technicianRepository.findById(1L))
                .thenReturn(Optional.of(tech));

        technicianService.delete(1L);

        verify(technicianRepository).delete(tech);
    }

    @Test
    void delete_nonExistingTechnician_throwsException() {
        when(technicianRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(TechnicianNotFoundException.class,
                () -> technicianService.delete(1L));
    }
}
