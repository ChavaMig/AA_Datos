package com.example.aa1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "technicians")
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is mandatory")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "surname is mandatory")
    @Column(nullable = false)
    private String surname;

    @Email(message = "email must be valid")
    @NotBlank(message = "email is mandatory")
    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String phone;

    @Column(nullable = false)
    private boolean active = true;
}
