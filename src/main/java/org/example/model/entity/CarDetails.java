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

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(unique = true)
    private String plateNumber;

    @ManyToOne
    @JoinColumn(name = "fuel_type_id")
    private FuelType fuelType;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seating_capacity_id")
    private SeatingCapacity seatingCapacity;

    private Double dailyRate;
    private String status; // Available, Maintenance, Rent වගේ status String එකක් විදිහට තියෙන එක පහසුයි
    private Integer year;

    @Column(length = 1000)
    private String description;

    private String imageUrl;

    private Double currentLat;
    private Double currentLng;
}