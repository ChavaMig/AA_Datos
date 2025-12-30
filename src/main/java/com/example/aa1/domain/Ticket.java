package com.example.aa1.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "description is mandatory")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "open date is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "open_date", nullable = false)
    private LocalDate openDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "close_date")
    private LocalDate closeDate;

    @NotBlank(message = "status is mandatory")
    @Column(nullable = false)
    private String status;

    // Relación con Machine
    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;

    // Relación con Technician
    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

}
