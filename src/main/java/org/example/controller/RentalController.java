package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.request.RentalRequestDTO;
import org.example.model.dto.response.RentalResponseDTO;
import org.example.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rentals")
@RequiredArgsConstructor
@CrossOrigin
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("/create")
    public ResponseEntity<RentalResponseDTO> createRental(@RequestBody RentalRequestDTO dto) {
        return ResponseEntity.ok(rentalService.createRental(dto));
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<RentalResponseDTO> completeRental(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.completeRental(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RentalResponseDTO>> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/my-rentals")
    public ResponseEntity<List<RentalResponseDTO>> getMyRentals(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(rentalService.getRentalsByEmail(email));
    }
}