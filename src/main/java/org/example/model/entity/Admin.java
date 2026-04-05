package org.example.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private String userName;

    @Column(unique = true, nullable = false)
    private String email;

    private String employeeId;
    @OneToOne
    @JoinColumn(name = "user_account_id") // Foreign Key එක
    private User user; // මේක ඇතුළේ තමයි අර User ID 6 තියෙන්නේ.
}