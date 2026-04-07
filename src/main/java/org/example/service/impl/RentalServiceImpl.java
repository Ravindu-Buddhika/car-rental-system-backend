package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.CarDetails;
import org.example.model.entity.Rental;
import org.example.repository.CarRepository;
import org.example.repository.RentalRepository;
import org.example.service.RentalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;

    @Override
    @Transactional
    public Rental createRental(Rental rental) {
        CarDetails car = carRepository.findById(rental.getCar().getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found!"));

        if (!"Available".equalsIgnoreCase(car.getStatus())) {
            throw new RuntimeException("මෙම වාහනය දැනට ලබාගත නොහැක (Already Rented/Maintenance)");
        }

        long days = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
        if (days <= 0) days = 1;

        rental.setTotalAmount(days * car.getDailyRate());
        rental.setRentalStatus("Active");
        rental.setPenaltyFee(0.0);


        car.setStatus("Rented");
        carRepository.save(car);

        return rentalRepository.save(rental);
    }

    @Override
    @Transactional
    public Rental completeRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental record not found!"));

        LocalDate today = LocalDate.now();
        rental.setActualReturnDate(today);
        rental.setRentalStatus("Completed");

        long lateDays = ChronoUnit.DAYS.between(rental.getEndDate(), today);

        if (lateDays > 0) {
            double penaltyPerDay = 500.0;
            double totalPenalty = lateDays * penaltyPerDay;

            rental.setPenaltyFee(totalPenalty);
            rental.setTotalAmount(rental.getTotalAmount() + totalPenalty);
        } else {
            rental.setPenaltyFee(0.0);
        }

        CarDetails car = rental.getCar();
        car.setStatus("Available");
        carRepository.save(car);

        return rentalRepository.save(rental);
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public List<Rental> getRentalsByEmail(String email) {
        return rentalRepository.findByCustomer_User_Email(email);
    }
}
