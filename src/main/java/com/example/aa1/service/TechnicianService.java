package com.example.aa1.service;

import com.example.aa1.domain.Technician;
import com.example.aa1.exception.TechnicianNotFoundException;
import com.example.aa1.repository.TechnicianRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Technician add(Technician technician) {
        return technicianRepository.save(technician);
    }

    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    public Technician findById(long id) throws TechnicianNotFoundException {
        return technicianRepository.findById(id)
                .orElseThrow(() -> new TechnicianNotFoundException("Technician not found"));
    }

    public Technician modify(long id, Technician technician) throws TechnicianNotFoundException {
        Technician existingTechnician = technicianRepository.findById(id)
                .orElseThrow(() -> new TechnicianNotFoundException("Technician not found"));

        modelMapper.map(technician, existingTechnician);
        existingTechnician.setId(id);

        return technicianRepository.save(existingTechnician);
    }

    public void delete(long id) throws TechnicianNotFoundException {
        Technician technician = technicianRepository.findById(id)
                .orElseThrow(() -> new TechnicianNotFoundException("Technician not found"));

        technicianRepository.delete(technician);
    }
}
