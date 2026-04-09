package com.example.aa1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "ClinicV2")
@Table(name = "clinics")
public class ClinicV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "name is mandatory")
    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String email;

    // --- CAMBIO V2 ---
    @Column
    @NotBlank(message = "website is mandatory")
    private String website;

    @OneToMany(mappedBy = "clinic")
    @JsonIgnoreProperties("clinic")
    private List<Machine> machines;
}