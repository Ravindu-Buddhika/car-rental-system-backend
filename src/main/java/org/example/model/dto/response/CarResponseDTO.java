package org.example.model.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDTO {
    private Long carId;
    private String carModel;
    private String brandName;         // උදා: "Toyota"
    private String plateNumber;
    private String fuelTypeName;      // උදා: "Petrol"
    private String transmissionType;  // උදා: "Auto"
    private String categoryName;      // උදා: "Luxury"
    private Integer seatingCapacity;  // උදා: 5
    private Double dailyRate;
    private String status;
    private Integer year;
    private String description;
    private String imageUrl;
    private Double currentLat;
    private Double currentLng;
}