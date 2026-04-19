package org.example.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private CarDetails car;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualReturnDate;

    private Double totalAmount;
    private Double penaltyFee;
    private String rentalStatus;
}