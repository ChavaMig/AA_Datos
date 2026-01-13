package com.example.aa1.repository;

import com.example.aa1.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t WHERE t.technician.id = :techId")
    List<Ticket> findByTechnicianId(@Param("techId") Long techId);

    //  JPQL ticket by status

    @Query("SELECT t FROM Ticket t WHERE t.status = :status")
    List<Ticket> findByStatusJPQL(@Param("status") String status);
}
