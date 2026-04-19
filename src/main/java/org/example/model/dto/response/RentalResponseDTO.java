package org.example.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponseDTO {private Long rentalId;
    // Car Details (Names)
    private String carModel;
    private String plateNumber;
    // Customer Details
    private String customerName;
    private String contactNumber;
    // Dates
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalCost;
    private String status;

}
