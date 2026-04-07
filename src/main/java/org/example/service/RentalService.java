package org.example.service;

import org.example.model.entity.Rental;

public interface RentalService {
    Rental createRental(Rental rental);
    Rental completeRental(Long rentalId);
}
