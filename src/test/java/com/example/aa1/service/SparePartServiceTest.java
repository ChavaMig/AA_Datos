package com.example.aa1.service;

import com.example.aa1.domain.SparePart;
import com.example.aa1.exception.SparePartNotFoundException;
import com.example.aa1.repository.SparePartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SparePartServiceTest {

    @Mock
    private SparePartRepository sparePartRepository;

    @InjectMocks
    private SparePartService sparePartService;

    // ---------- FIND ALL ----------

    @Test
    void findAll_returnsList() {
        SparePart sp = SparePart.builder().id(1L).build();
        when(sparePartRepository.findAll()).thenReturn(List.of(sp));

        List<SparePart> result = sparePartService.findAll();

        assertEquals(1, result.size());
        verify(sparePartRepository).findAll();
    }

    // ---------- FIND BY ID ----------

    @Test
    void findById_existing_returnsSparePart() throws SparePartNotFoundException {
        SparePart sp = SparePart.builder().id(1L).build();
        when(sparePartRepository.findById(1L)).thenReturn(Optional.of(sp));

        SparePart result = sparePartService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void findById_nonExisting_throwsException() {
        when(sparePartRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SparePartNotFoundException.class,
                () -> sparePartService.findById(1L));
    }

    // ---------- ADD ----------

    @Test
    void add_validSparePart_savesSparePart() {
        SparePart sp = SparePart.builder().name("New").build();
        when(sparePartRepository.save(sp)).thenReturn(sp);

        SparePart result = sparePartService.add(sp);

        assertNotNull(result);
        verify(sparePartRepository).save(sp);
    }

    // ---------- MODIFY ----------

    @Test
    void modify_existing_updatesFields() throws SparePartNotFoundException {
        SparePart existing = SparePart.builder()
                .id(1L)
                .name("Old")
                .price(10.0)
                .stock(5)
                .build();

        SparePart updated = SparePart.builder()
                .name("New")
                .price(20.0)
                .stock(8)
                .build();

        when(sparePartRepository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(sparePartRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SparePart result = sparePartService.modify(1L, updated);

        assertEquals("New", result.getName());
        assertEquals(20.0, result.getPrice());
        assertEquals(8, result.getStock());
    }

    @Test
    void modify_nonExisting_throwsException() {
        when(sparePartRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SparePartNotFoundException.class,
                () -> sparePartService.modify(1L, new SparePart()));
    }

    // ---------- PATCH ----------

    @Test
    void patch_existing_updatesFields() throws SparePartNotFoundException {
        SparePart existing = SparePart.builder()
                .id(1L)
                .name("Old")
                .stock(5)
                .build();

        when(sparePartRepository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(sparePartRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Map<String, Object> updates = Map.of(
                "name", "New",
                "stock", 10
        );

        SparePart result = sparePartService.patch(1L, updates);

        assertEquals("New", result.getName());
        assertEquals(10, result.getStock());
    }

    @Test
    void patch_nonExisting_throwsException() {
        when(sparePartRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SparePartNotFoundException.class,
                () -> sparePartService.patch(1L, Map.of()));
    }

    // ---------- DELETE ----------

    @Test
    void delete_existing_deletesSparePart() throws SparePartNotFoundException {
        SparePart sp = SparePart.builder().id(1L).build();

        when(sparePartRepository.findById(1L))
                .thenReturn(Optional.of(sp));

        sparePartService.delete(1L);

        verify(sparePartRepository).delete(sp);
    }


    @Test
    void delete_nonExisting_throwsException() {
        assertThrows(SparePartNotFoundException.class,
                () -> sparePartService.delete(1L));
    }
}
