package com.example.aa1.service;

import com.example.aa1.domain.Machine;
import com.example.aa1.exception.MachineNotFoundException;
import com.example.aa1.repository.MachineRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class MachineService {

    private final MachineRepository machineRepository;

    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public Machine add(Machine machine) {
        return machineRepository.save(machine);
    }

    //  GET CON FILTRADO

    public List<Machine> findWithFilters(
            String serialNumber,
            String model,
            String manufacturer
    ) {

        List<Machine> machines = machineRepository.findAll();

        return machines.stream()
                .filter(m -> serialNumber == null ||
                        m.getSerialNumber()
                                .toLowerCase()
                                .contains(serialNumber.toLowerCase()))
                .filter(m -> model == null ||
                        m.getModel()
                                .toLowerCase()
                                .contains(model.toLowerCase()))
                .filter(m -> manufacturer == null ||
                        m.getManufacturer()
                                .toLowerCase()
                                .contains(manufacturer.toLowerCase()))
                .toList();
    }

    public List<Machine> findAll() {
        return machineRepository.findAll();
    }

    public Machine findById(Long id) throws MachineNotFoundException {
        return machineRepository.findById(id)
                .orElseThrow(() -> new MachineNotFoundException("Machine not found"));
    }

    public Machine modify(Long id, Machine machine)
            throws MachineNotFoundException {

        Machine existingMachine = machineRepository.findById(id)
                .orElseThrow(() -> new MachineNotFoundException("Machine not found"));

        existingMachine.setSerialNumber(machine.getSerialNumber());
        existingMachine.setModel(machine.getModel());
        existingMachine.setManufacturer(machine.getManufacturer());

        return machineRepository.save(existingMachine);
    }

    //  PATCH

    public Machine patch(Long id, Map<String, Object> updates)
            throws MachineNotFoundException {

        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new MachineNotFoundException("Machine not found"));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Machine.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, machine, value);
            }
        });

        return machineRepository.save(machine);
    }

    public void delete(Long id) throws MachineNotFoundException {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new MachineNotFoundException("Machine not found"));

        machineRepository.delete(machine);
    }

    //  JPQL

    public List<Machine> findByClinicId(Long clinicId) {
        return machineRepository.findByClinicId(clinicId);
    }
}
