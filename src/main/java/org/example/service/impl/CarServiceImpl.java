package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.CarDetails;
import org.example.repository.CarRepository;
import org.example.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public CarDetails saveCar(CarDetails car) {
        return carRepository.save(car);
    }

    @Override
    public List<CarDetails> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public CarDetails getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }
}
