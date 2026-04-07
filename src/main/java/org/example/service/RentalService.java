package org.example.service;

import org.example.model.entity.Rental;

import java.util.List;

public interface RentalService {
    Rental createRental(Rental rental);
    Rental completeRental(Long rentalId);
    List<Rental> getAllRentals();
}
