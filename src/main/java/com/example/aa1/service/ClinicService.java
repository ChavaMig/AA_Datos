package com.example.aa1.service;

import com.example.aa1.domain.Clinic;
import com.example.aa1.dto.ClinicDto;
import com.example.aa1.dto.ClinicOutDto;
import com.example.aa1.exception.ClinicNotFoundException;
import com.example.aa1.repository.ClinicRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Clinic add(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    // ===================== GET CON FILTRADO =====================

    public List<ClinicOutDto> findWithFilters(
            String name,
            String address,
            String phone
    ) {

        List<Clinic> clinics = clinicRepository.findAll();

        List<Clinic> filtered = clinics.stream()
                .filter(c -> name == null ||
                        c.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(c -> address == null ||
                        (c.getAddress() != null &&
                                c.getAddress().toLowerCase().contains(address.toLowerCase())))
                .filter(c -> phone == null ||
                        (c.getPhone() != null &&
                                c.getPhone().contains(phone)))
                .toList();

        return modelMapper.map(
                filtered,
                new TypeToken<List<ClinicOutDto>>() {}.getType()
        );
    }

    public void delete(long id) throws ClinicNotFoundException {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ClinicNotFoundException("Clinica no encontrada"));

        clinicRepository.delete(clinic);
    }

    public ClinicDto findById(long id) throws ClinicNotFoundException {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ClinicNotFoundException("Clinica no encontrada"));

        return modelMapper.map(clinic, ClinicDto.class);
    }

    public Clinic modify(long id, Clinic clinic)
            throws ClinicNotFoundException {

        Clinic existingClinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ClinicNotFoundException("Clinica no encontrada"));

        modelMapper.map(clinic, existingClinic);
        existingClinic.setId(id);

        return clinicRepository.save(existingClinic);
    }

    // ===================== PATCH =====================

    public Clinic patch(long id, Map<String, Object> updates)
            throws ClinicNotFoundException {

        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ClinicNotFoundException("Clinica no encontrada"));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Clinic.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, clinic, value);
            }
        });

        return clinicRepository.save(clinic);
    }

    // ⚠️ JPQL se mantiene para OTRO punto del trabajo
    public List<Clinic> findByName(String name) {
        return clinicRepository.findByNameContaining(name);
    }
}
