package com.example.aa1.repository;

import com.example.aa1.domain.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    @Query("SELECT c FROM Clinic c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Clinic> findByNameContaining(@Param("name") String name);
}
