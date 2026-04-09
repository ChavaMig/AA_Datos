package com.example.aa1.repository;

import com.example.aa1.domain.ClinicV2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepositoryV2 extends CrudRepository<ClinicV2, Long> {
}