package org.example.model.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequestDTO {
    private String carModel;
    private Long brandId;            // Brand ID එක විතරයි
    private String plateNumber;
    private Long fuelTypeId;         // Fuel Type ID
    private Long transmissionId;     // Transmission ID
    private Long categoryId;         // Category ID
    private Long seatingCapacityId;  // Seating Capacity ID
    private Double dailyRate;
    private String status;           // "Available", "Maintenance", etc.
    private Integer year;
    private String description;
    private String imageUrl;
    private Double currentLat;
    private Double currentLng;
}