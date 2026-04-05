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

    private String contactNumber;
    private String nic;
    private String address;
    private String drivingLicenseNumber;

    @OneToOne
    @JoinColumn(name = "user_account_id") // Foreign Key එක
    private User user; // මේක ඇතුළේ තමයි අර User ID 6 තියෙන්නේ.
}