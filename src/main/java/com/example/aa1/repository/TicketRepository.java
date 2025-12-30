package com.example.aa1.repository;

import com.example.aa1.domain.Ticket;
import com.example.aa1.domain.Technician;
import com.example.aa1.domain.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Tickets asignados a un técnico
    List<Ticket> findByTechnician(Technician technician);

    // Tickets de una máquina
    List<Ticket> findByMachine(Machine machine);

    // Tickets por estado
    List<Ticket> findByStatus(String status);
}
