package com.example.aa1.repository;

import com.example.aa1.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    //  JPQL Technician by active

    @Query("SELECT t FROM Technician t WHERE t.active = :active")
    List<Technician> findByActive(@Param("active") boolean active);
}
