package com.example.aa1.service;

import com.example.aa1.domain.Machine;
import com.example.aa1.exception.MachineNotFoundException;
import com.example.aa1.repository.MachineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {

    private final MachineRepository machineRepository;

    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    // CREATE
    public Machine add(Machine machine) {
        return machineRepository.save(machine);
    }

    // READ ALL
    public List<Machine> findAll() {
        return machineRepository.findAll();
    }

    // READ ONE
    public Machine findById(Long id) throws MachineNotFoundException {
        return machineRepository.findById(id)
                .orElseThrow(() -> new MachineNotFoundException("Machine not found"));
    }

    // UPDATE
    public Machine modify(Long id, Machine machine) throws MachineNotFoundException {
        Machine existingMachine = machineRepository.findById(id)
                .orElseThrow(() -> new MachineNotFoundException("Machine not found"));

        existingMachine.setSerialNumber(machine.getSerialNumber());
        existingMachine.setModel(machine.getModel());
        existingMachine.setManufacturer(machine.getManufacturer());

        return machineRepository.save(existingMachine);
    }

    // DELETE
    public void delete(Long id) throws MachineNotFoundException {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new MachineNotFoundException("Machine not found"));

        machineRepository.delete(machine);
    }
}
