package com.example.aa1.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clinics")
public class Clinic {

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

    //Relacion con la lista de machines
    @OneToMany(mappedBy = "clinic")
    @JsonManagedReference // El "padre" gestiona la referencia
    private List<Machine> machines;

}
