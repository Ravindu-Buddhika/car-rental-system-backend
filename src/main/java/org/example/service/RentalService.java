package org.example.service;

import org.example.model.dto.request.RentalRequestDTO;
import org.example.model.dto.response.MyRentalResponseDTO;
import org.example.model.dto.response.RentalResponseDTO;
import org.example.model.entity.Rental;

import java.util.List;

public interface RentalService {
    RentalResponseDTO createRental(RentalRequestDTO dto, String email);
    RentalResponseDTO completeRental(Long rentalId);
    List<RentalResponseDTO> getAllRentals();
    List<MyRentalResponseDTO> getRentalsByEmail(String email);
    RentalResponseDTO cancelRental(Long rentalId);
}
