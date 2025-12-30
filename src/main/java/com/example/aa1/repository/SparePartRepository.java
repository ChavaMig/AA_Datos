package com.example.aa1.repository;

import com.example.aa1.domain.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SparePartRepository extends JpaRepository<SparePart, Long> {

    List<SparePart> findAll();

    boolean existsBySerialNumber(String serialNumber);
}
