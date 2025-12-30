package com.example.aa1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "spare_parts")
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is mandatory")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "serial number is mandatory")
    @Column(name = "serial_number", unique = true, nullable = false)
    private String serialNumber;

    @NotBlank(message = "manufacturer is mandatory")
    @Column(nullable = false)
    private String manufacturer;

    @NotNull(message = "price is mandatory")
    @Min(value = 0, message = "price must be positive")
    private Double price;

    @NotNull(message = "stock is mandatory")
    @Min(value = 0, message = "stock must be zero or positive")
    private Integer stock;
}
