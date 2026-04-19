package org.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDTO {
    private Long carId;
    private Long customerId;
    private LocalDate startDate;
    private LocalDate endDate;
}
