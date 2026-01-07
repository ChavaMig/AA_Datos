package com.example.aa1.service;

import com.example.aa1.domain.SparePart;
import com.example.aa1.exception.SparePartNotFoundException;
import com.example.aa1.repository.SparePartRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class SparePartService {

    private final SparePartRepository sparePartRepository;

    public SparePartService(SparePartRepository sparePartRepository) {
        this.sparePartRepository = sparePartRepository;
    }

    // ===================== GET CON FILTRADO =====================

    public List<SparePart> findWithFilters(
            String name,
            String manufacturer,
            Double price
    ) {

        List<SparePart> spareParts = sparePartRepository.findAll();

        return spareParts.stream()
                .filter(sp -> name == null ||
                        sp.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(sp -> manufacturer == null ||
                        sp.getManufacturer().toLowerCase().contains(manufacturer.toLowerCase()))
                .filter(sp -> price == null ||
                        sp.getPrice().equals(price))
                .toList();
    }

    public List<SparePart> findAll() {
        return sparePartRepository.findAll();
    }

    public SparePart findById(Long id) throws SparePartNotFoundException {
        return sparePartRepository.findById(id)
                .orElseThrow(SparePartNotFoundException::new);
    }

    public SparePart add(SparePart sparePart) {
        return sparePartRepository.save(sparePart);
    }

    public SparePart modify(Long id, SparePart sparePart)
            throws SparePartNotFoundException {

        SparePart existing = sparePartRepository.findById(id)
                .orElseThrow(SparePartNotFoundException::new);

        sparePart.setId(existing.getId());
        return sparePartRepository.save(sparePart);
    }

    // ===================== PATCH =====================

    public SparePart patch(Long id, Map<String, Object> updates)
            throws SparePartNotFoundException {

        SparePart sparePart = sparePartRepository.findById(id)
                .orElseThrow(SparePartNotFoundException::new);

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(SparePart.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, sparePart, value);
            }
        });

        return sparePartRepository.save(sparePart);
    }

    public void delete(Long id) throws SparePartNotFoundException {
        SparePart sparePart = sparePartRepository.findById(id)
                .orElseThrow(SparePartNotFoundException::new);

        sparePartRepository.delete(sparePart);
    }
}
