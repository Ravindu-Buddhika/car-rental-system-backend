package org.example.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDashboardDTO {
    private double totalRevenue;
    private long activeBookings;
    private long totalVehicles;
    private long pendingRequests;
    private long overdueDropOffs;
    private long todayBookings;

    private Map<String, Long> vehicleStatusCounts;
    private List<Map<String, Object>> monthlyRevenue;
    private List<RentalResponseDTO> recentBookings;
    private List<RentalResponseDTO> overdueBookings;
}
