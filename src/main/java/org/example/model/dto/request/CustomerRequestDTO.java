package org.example.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {
    private String email;
    private String password;
    private String fullName;
    private String contactNumber;
    private String nic;
    private String address;
    private String drivingLicenseNumber;
}
