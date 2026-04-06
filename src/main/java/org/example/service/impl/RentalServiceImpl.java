package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.CarDetails;
import org.example.model.entity.Rental;
import org.example.repository.CarRepository;
import org.example.repository.RentalRepository;
import org.example.service.RentalService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    @Override
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
}
