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

import java.util.List;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Clinic add(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public void delete(long id) throws ClinicNotFoundException {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ClinicNotFoundException("Clinica no encontrada"));

        clinicRepository.delete(clinic);
    }

    public List<ClinicOutDto> findAll() {
        List<Clinic> clinics = clinicRepository.findAll();
        return modelMapper.map(clinics, new TypeToken<List<ClinicOutDto>>() {}.getType());
    }

    public ClinicDto findById(long id) throws ClinicNotFoundException {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ClinicNotFoundException("Clinica no encontrada"));

        return modelMapper.map(clinic, ClinicDto.class);
    }

    public Clinic modify(long id, Clinic clinic) throws ClinicNotFoundException {
        Clinic existingClinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ClinicNotFoundException("Clinica no encontrada"));

        modelMapper.map(clinic, existingClinic);
        existingClinic.setId(id);

        return clinicRepository.save(existingClinic);
    }
}
