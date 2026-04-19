package org.example.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String contactNumber;

    @Column(unique = true, nullable = false)
    private String nic;

    private String address;

    @Column(unique = true, nullable = false)
    private String drivingLicenseNumber;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private User user;
}