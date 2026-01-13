package com.example.aa1.service;

import com.example.aa1.domain.Clinic;
import com.example.aa1.dto.ClinicDto;
import com.example.aa1.exception.ClinicNotFoundException;
import com.example.aa1.repository.ClinicRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClinicServiceTest {

    @Mock
    private ClinicRepository clinicRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClinicService clinicService;


    // CREATE

    @Test
    void add_validClinic_savesClinic() {
        Clinic clinic = Clinic.builder()
                .name("Clinica Central")
                .address("Calle Mayor")
                .build();

        when(clinicRepository.save(clinic)).thenReturn(clinic);

        Clinic result = clinicService.add(clinic);

        assertNotNull(result);
        assertEquals("Clinica Central", result.getName());
        verify(clinicRepository).save(clinic);
    }

    // READ BY ID (OK)

    @Test
    void findById_existingClinic_returnsDto() throws Exception {
        Clinic clinic = Clinic.builder()
                .id(1L)
                .name("Clinica Central")
                .address("Calle Mayor")
                .build();

        ClinicDto dto = new ClinicDto();
        dto.setId(1L);
        dto.setName("Clinica Central");
        dto.setAddress("Calle Mayor");

        when(clinicRepository.findById(1L))
                .thenReturn(Optional.of(clinic));
        when(modelMapper.map(clinic, ClinicDto.class))
                .thenReturn(dto);

        ClinicDto result = clinicService.findById(1L);

        assertNotNull(result);
        assertEquals("Clinica Central", result.getName());
        verify(clinicRepository).findById(1L);
    }


    // READ BY ID (NOT FOUND)

    @Test
    void findById_nonExistingClinic_throwsException() {
        when(clinicRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(ClinicNotFoundException.class,
                () -> clinicService.findById(99L));
    }


    // READ ALL

    @Test
    void findAll_returnsClinicOutDtoList() {
        List<Clinic> clinics = List.of(
                Clinic.builder().id(1L).name("A").build(),
                Clinic.builder().id(2L).name("B").build()
        );

        when(clinicRepository.findAll()).thenReturn(clinics);



        when(modelMapper.map(anyList(), any(java.lang.reflect.Type.class)))
                .thenReturn(Collections.emptyList());

        var result = clinicService.findAll();

        assertNotNull(result);
        verify(clinicRepository).findAll();
    }



    // UPDATE (PUT) OK

    @Test
    void modify_existingClinic_updatesClinic() throws Exception {
        Clinic existing = Clinic.builder()
                .id(1L)
                .name("Old")
                .address("Old address")
                .build();

        Clinic updated = Clinic.builder()
                .name("New")
                .address("New address")
                .build();

        when(clinicRepository.findById(1L))
                .thenReturn(Optional.of(existing));
        when(clinicRepository.save(existing))
                .thenReturn(existing);

        Clinic result = clinicService.modify(1L, updated);

        assertEquals(1L, result.getId());
        verify(clinicRepository).save(existing);
    }


    // UPDATE (PUT) NOT FOUND

    @Test
    void modify_nonExistingClinic_throwsException() {
        when(clinicRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(ClinicNotFoundException.class,
                () -> clinicService.modify(99L, new Clinic()));
    }


    // UPDATE (PATCH) OK

    @Test
    void patch_existingClinic_updatesFields() throws Exception {
        Clinic clinic = Clinic.builder()
                .id(1L)
                .name("Old")
                .address("Old address")
                .build();

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "New");

        when(clinicRepository.findById(1L))
                .thenReturn(Optional.of(clinic));
        when(clinicRepository.save(clinic))
                .thenReturn(clinic);

        Clinic result = clinicService.patch(1L, updates);

        assertEquals("New", result.getName());
        verify(clinicRepository).save(clinic);
    }


    // UPDATE (PATCH) NOT FOUND

    @Test
    void patch_nonExistingClinic_throwsException() {
        when(clinicRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(ClinicNotFoundException.class,
                () -> clinicService.patch(99L, Map.of("name", "X")));
    }


    // DELETE OK

    @Test
    void delete_existingClinic_deletesClinic() throws Exception {
        Clinic clinic = Clinic.builder()
                .id(1L)
                .name("Clinica Central")
                .build();

        when(clinicRepository.findById(1L))
                .thenReturn(Optional.of(clinic));

        clinicService.delete(1L);

        verify(clinicRepository).delete(clinic);
    }


    // DELETE NOT FOUND

    @Test
    void delete_nonExistingClinic_throwsException() {
        when(clinicRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(ClinicNotFoundException.class,
                () -> clinicService.delete(99L));
    }
}
