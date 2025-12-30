package com.example.aa1.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    //Asignar maquinas a clinicas mediante el nombre
    @ManyToOne
    @JoinColumn(name = "clinic_id")
    @JsonBackReference // Evita volver a la clínica y entrar en bucle
    private Clinic clinic;

}
