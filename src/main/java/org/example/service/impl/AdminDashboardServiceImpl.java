package org.example.service.impl;

import lombok.RequiredArgsConstructor;


import org.example.model.dto.response.AdminDashboardDTO;
import org.example.model.dto.response.RentalResponseDTO;
import org.example.model.entity.Rental;
import org.example.repository.CarRepository;
import org.example.repository.RentalRepository;
import org.example.service.AdminDashboardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;

    @Override
    public AdminDashboardDTO getDashboardData() {
        AdminDashboardDTO dashboard = new AdminDashboardDTO();
        dashboard.setTotalRevenue(rentalRepository.getTotalRevenue() != null ? rentalRepository.getTotalRevenue() : 0.0);
        dashboard.setActiveBookings(rentalRepository.countByRentalStatus("Active"));
        dashboard.setTotalVehicles(carRepository.count());
        dashboard.setPendingRequests(rentalRepository.countByRentalStatus("Pending"));
        dashboard.setTodayBookings(rentalRepository.countByStartDate(LocalDate.now()));


        long overdueCount = rentalRepository.countOverdueRentals(LocalDate.now());
        dashboard.setOverdueDropOffs(overdueCount);

        List<Rental> recentList = rentalRepository.findTop5ByOrderByRentalIdDesc();
        dashboard.setRecentBookings(recentList.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList()));

        List<Rental> overdueList = rentalRepository.findOverdueRentals(LocalDate.now());
        dashboard.setOverdueBookings(overdueList.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList()));

        return dashboard;
    }

    private RentalResponseDTO mapToResponseDTO(Rental rental) {
        RentalResponseDTO dto = new RentalResponseDTO();
        dto.setRentalId(rental.getRentalId());
        dto.setCarModel(rental.getCar().getCarModel());
        dto.setPlateNumber(rental.getCar().getPlateNumber());
        dto.setCustomerName(rental.getCustomer().getFullName());
        dto.setContactNumber(rental.getCustomer().getContactNumber());
        dto.setStartDate(rental.getStartDate());
        dto.setEndDate(rental.getEndDate());
        dto.setTotalCost(rental.getTotalAmount());
        dto.setStatus(rental.getRentalStatus());
        return dto;
    }
}