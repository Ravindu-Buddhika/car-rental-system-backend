package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.request.RentalRequestDTO;
import org.example.model.dto.response.MyRentalResponseDTO;
import org.example.model.dto.response.RentalResponseDTO;
import org.example.model.entity.CarDetails;
import org.example.model.entity.Customer;
import org.example.model.entity.Rental;
import org.example.repository.CarRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.RentalRepository;
import org.example.service.EmailService;
import org.example.service.RentalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public RentalResponseDTO createRental(RentalRequestDTO dto, String email) {
        CarDetails car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found!"));

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer profile not found for email: " + email));

        // 3. Availability Check
        if (!"Available".equalsIgnoreCase(car.getStatus())) {
            throw new RuntimeException("This vehicle is not available for now.");
        }

        long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());
        if (days <= 0) days = 1;

        Rental rental = new Rental();
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setStartDate(dto.getStartDate());
        rental.setEndDate(dto.getEndDate());
        rental.setTotalAmount(days * car.getDailyRate());
        rental.setRentalStatus("Active");
        rental.setPenaltyFee(0.0);

        car.setStatus("Rented");
        carRepository.save(car);

        Rental savedRental = rentalRepository.save(rental);

        try {
            emailService.sendBookingConfirmation(savedRental);
        } catch (Exception e) {
            System.err.println("Email Error: " + e.getMessage());
        }

        return mapToResponseDTO(savedRental);
    }

    @Override
    @Transactional
    public RentalResponseDTO completeRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental record not found!"));

        LocalDate today = LocalDate.now();
        rental.setActualReturnDate(today);
        rental.setRentalStatus("Completed");

        long lateDays = ChronoUnit.DAYS.between(rental.getEndDate(), today);
        if (lateDays > 0) {
            double totalPenalty = lateDays * 500.0;
            rental.setPenaltyFee(totalPenalty);
            rental.setTotalAmount(rental.getTotalAmount() + totalPenalty);
        }

        rental.getCar().setStatus("Available");
        carRepository.save(rental.getCar());

        return mapToResponseDTO(rentalRepository.save(rental));
    }

    @Override
    public List<RentalResponseDTO> getAllRentals() {
        return rentalRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MyRentalResponseDTO> getRentalsByEmail(String email) {
        return rentalRepository.findByCustomer_User_Email(email).stream()
                .map(this::mapToMyRentalResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RentalResponseDTO cancelRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental record not found!"));

        if ("Completed".equalsIgnoreCase(rental.getRentalStatus())) {
            throw new RuntimeException("Completed bookings cannot be cancelled.");
        }

        rental.setRentalStatus("Cancelled");

        rental.getCar().setStatus("Available");
        carRepository.save(rental.getCar());

        return mapToResponseDTO(rentalRepository.save(rental));
    }

    // --- Mapper Methods ---

    private RentalResponseDTO mapToResponseDTO(Rental rental) {
        RentalResponseDTO resp = new RentalResponseDTO();
        resp.setRentalId(rental.getRentalId());
        resp.setCarModel(rental.getCar().getCarModel());
        resp.setPlateNumber(rental.getCar().getPlateNumber());
        resp.setCustomerName(rental.getCustomer().getFullName());
        resp.setContactNumber(rental.getCustomer().getContactNumber());
        resp.setStartDate(rental.getStartDate());
        resp.setEndDate(rental.getEndDate());
        resp.setTotalCost(rental.getTotalAmount());
        resp.setStatus(rental.getRentalStatus());
        return resp;
    }

    private MyRentalResponseDTO mapToMyRentalResponseDTO(Rental rental) {
        MyRentalResponseDTO dto = new MyRentalResponseDTO();
        dto.setRentalId(rental.getRentalId());

        if (rental.getCar() != null) {
            dto.setCarModel(rental.getCar().getCarModel());
            dto.setPlateNumber(rental.getCar().getPlateNumber());
            dto.setImageUrl(rental.getCar().getImageUrl());
        }

        dto.setStartDate(rental.getStartDate());
        dto.setEndDate(rental.getEndDate());
        dto.setTotalCost(rental.getTotalAmount());
        dto.setStatus(rental.getRentalStatus());

        return dto;
    }
}