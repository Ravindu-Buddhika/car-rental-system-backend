package org.example.service;

import org.example.model.entity.Rental;

public interface EmailService {
    void sendBookingConfirmation(Rental rental);
}
