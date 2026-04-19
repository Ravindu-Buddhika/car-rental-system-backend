package org.example.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequestDTO {
    private String email;
    private String password;
    private String userName;
    private String employeeId;
    private String department;
}
