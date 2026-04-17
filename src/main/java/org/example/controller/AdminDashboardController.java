package org.example.controller;

import lombok.RequiredArgsConstructor;

import org.example.model.dto.response.AdminDashboardDTO;
import org.example.service.AdminDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AdminDashboardController {

    private final AdminDashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<AdminDashboardDTO> getDashboardStats() {
        return ResponseEntity.ok(dashboardService.getDashboardData());
    }
}