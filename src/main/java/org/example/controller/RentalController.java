package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.Rental;
import org.example.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rentals")
@RequiredArgsConstructor
@CrossOrigin
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("/create")
    public ResponseEntity<Rental> createRental(@RequestBody Rental rental) {
        return ResponseEntity.ok(rentalService.createRental(rental));
    }
    @PutMapping("/return/{id}")
    public ResponseEntity<Rental> completeRental(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.completeRental(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Rental>> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }
    @GetMapping("/my-rentals")
    public ResponseEntity<List<Rental>> getMyRentals(Authentication authentication) {
        String email = authentication.getName();

        List<Rental> myRentals = rentalService.getRentalsByEmail(email);
        return ResponseEntity.ok(myRentals);
    }
}
