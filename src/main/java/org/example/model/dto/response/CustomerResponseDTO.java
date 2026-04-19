package org.example.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    private Long id; // Customer ID එක Frontend එකට ඕන වෙනවා
    private String email;
    private String fullName;
    private String contactNumber;
    private String nic;
    private String address;
    private String drivingLicenseNumber;
}
