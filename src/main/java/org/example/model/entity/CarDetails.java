package org.example.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class CarDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    private String carModel;
    private String brand;
    private String plateNumber;
    private String fuelType;
    private Double dailyRate;
    private String status; // Available, Rented, Maintenance
    private Integer year;

    @Column(length = 1000)
    private String description;


    private Double currentLat;
    private Double currentLng;
}