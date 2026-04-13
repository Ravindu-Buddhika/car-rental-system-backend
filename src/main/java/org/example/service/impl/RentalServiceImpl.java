package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.request.RentalRequestDTO;
import org.example.model.dto.response.RentalResponseDTO;
import org.example.model.entity.CarDetails;
import org.example.model.entity.Customer;
import org.example.model.entity.Rental;
import org.example.repository.CarRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.RentalRepository;
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

    @Override
    @Transactional
    public RentalResponseDTO createRental(RentalRequestDTO dto) {
        // 1. Car එක සහ Customer ව හොයාගන්නවා
        CarDetails car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found!"));
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        // 2. වාහනය Available ද කියලා බලනවා
        if (!"Available".equalsIgnoreCase(car.getStatus())) {
            throw new RuntimeException("මෙම වාහනය දැනට ලබාගත නොහැක.");
        }

        // 3. දින ගණන සහ මුළු මුදල ගණනය කරනවා
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

        // 4. වාහනයේ Status එක වෙනස් කරනවා
        car.setStatus("Rented");
        carRepository.save(car);

        return mapToResponseDTO(rentalRepository.save(rental));
    }

    @Override
    @Transactional
    public RentalResponseDTO completeRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental record not found!"));

        LocalDate today = LocalDate.now();
        rental.setActualReturnDate(today);
        rental.setRentalStatus("Completed");

        // දඩ මුදල (Penalty) ගණනය - දිනකට 500 බැගින්
        long lateDays = ChronoUnit.DAYS.between(rental.getEndDate(), today);
        if (lateDays > 0) {
            double totalPenalty = lateDays * 500.0;
            rental.setPenaltyFee(totalPenalty);
            rental.setTotalAmount(rental.getTotalAmount() + totalPenalty);
        }

        // වාහනය ආපහු Available කරනවා
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
    public List<RentalResponseDTO> getRentalsByEmail(String email) {
        return rentalRepository.findByCustomer_User_Email(email).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Helper Method for Mapping
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
}