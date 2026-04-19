package org.example.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequestDTO {
    private Long carId;
    private Long customerId;
    private LocalDate startDate;
    private LocalDate endDate;
}
