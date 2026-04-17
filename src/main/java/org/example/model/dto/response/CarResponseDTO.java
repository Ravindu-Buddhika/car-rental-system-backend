package org.example.model.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDTO {
    private Long carId;
    private String carModel;
    private String brandName;
    private String plateNumber;
    private String fuelTypeName;
    private String transmissionType;
    private String categoryName;
    private Integer seatingCapacity;
    private Double dailyRate;
    private String status;
    private Integer year;
    private String description;
    private String imageUrl;
    private Double currentLat;
    private Double currentLng;
}