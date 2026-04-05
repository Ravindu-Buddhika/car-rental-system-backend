package org.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    // User Account එකට අදාළ දත්ත
    private String email;
    private String password;

    // Customer Profile එකට අදාළ දත්ත
    private String fullName;
    private String contactNumber;
    private String nic;
    private String address;
    private String drivingLicenseNumber;
}