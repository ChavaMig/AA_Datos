package com.example.aa1.repository;

import com.example.aa1.domain.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    boolean existsBySerialNumber(String serialNumber);
}
