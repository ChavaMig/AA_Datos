package com.example.aa1.repository;

import com.example.aa1.domain.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    boolean existsBySerialNumber(String serialNumber);

    @Query("SELECT m FROM Machine m WHERE m.manufacturer = :manufacturer")
    List<Machine> findByManufacturer(@Param("manufacturer") String manufacturer);

    //  JPQL - Machines by Clinic

    @Query("SELECT m FROM Machine m WHERE m.clinic.id = :clinicId")
    List<Machine> findByClinicId(@Param("clinicId") Long clinicId);
}
