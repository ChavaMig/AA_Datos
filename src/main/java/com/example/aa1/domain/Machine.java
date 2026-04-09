package com.example.aa1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "machines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Serial number is mandatory")
    @Column(nullable = false, unique = true)
    private String serialNumber;

    @NotBlank(message = "Model is mandatory")
    @Column(nullable = false)
    private String model;

    @NotBlank(message = "Manufacturer is mandatory")
    @Column(nullable = false)
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    @JsonIgnoreProperties("machines")
    private Clinic clinic;
}