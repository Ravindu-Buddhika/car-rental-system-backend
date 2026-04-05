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

    @Column(unique = true)
    private String plateNumber;

    private String fuelType;
    private String transmission;
    private String category;
    private Integer seatingCapacity;

    private Double dailyRate;
    private String status;
    private Integer year;

    @Column(length = 1000)
    private String description;

    private String imageUrl;

    private Double currentLat;
    private Double currentLng;
}