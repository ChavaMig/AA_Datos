package com.example.aa1.service;

import com.example.aa1.domain.Technician;
import com.example.aa1.exception.TechnicianNotFoundException;
import com.example.aa1.repository.TechnicianRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final ModelMapper modelMapper;

    public TechnicianService(TechnicianRepository technicianRepository,
                             ModelMapper modelMapper) {
        this.technicianRepository = technicianRepository;
        this.modelMapper = modelMapper;
    }

    public Technician add(Technician technician) {
        return technicianRepository.save(technician);
    }

    // ===================== GET CON FILTRADO =====================

    public List<Technician> findWithFilters(
            String name,
            String email,
            Boolean active
    ) {

        List<Technician> technicians = technicianRepository.findAll();

        return technicians.stream()
                .filter(t -> name == null ||
                        t.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(t -> email == null ||
                        t.getEmail().toLowerCase().contains(email.toLowerCase()))
                .filter(t -> active == null ||
                        t.isActive() == active)
                .toList();
    }

    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    public Technician findById(long id) throws TechnicianNotFoundException {
        return technicianRepository.findById(id)
                .orElseThrow(() -> new TechnicianNotFoundException("Technician not found"));
    }

    public Technician modify(long id, Technician technician)
            throws TechnicianNotFoundException {

        Technician existing = technicianRepository.findById(id)
                .orElseThrow(() -> new TechnicianNotFoundException("Technician not found"));

        modelMapper.map(technician, existing);
        existing.setId(id);

        return technicianRepository.save(existing);
    }

    // ===================== PATCH =====================

    public Technician patch(long id, Map<String, Object> updates)
            throws TechnicianNotFoundException {

        Technician technician = technicianRepository.findById(id)
                .orElseThrow(() -> new TechnicianNotFoundException("Technician not found"));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Technician.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, technician, value);
            }
        });

        return technicianRepository.save(technician);
    }

    public void delete(long id) throws TechnicianNotFoundException {
        Technician technician = technicianRepository.findById(id)
                .orElseThrow(() -> new TechnicianNotFoundException("Technician not found"));

        technicianRepository.delete(technician);
    }

    // ===================== JPQL =====================

    public List<Technician> findByActive(boolean active) {
        return technicianRepository.findByActive(active);
    }
}
