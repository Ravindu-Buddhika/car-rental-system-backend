package org.example.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MyRentalResponseDTO {
    private Long rentalId;
    private String carModel;
    private String plateNumber;
    private String imageUrl;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalCost;
    private String status;
}


