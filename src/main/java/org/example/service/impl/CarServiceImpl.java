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
    @Override
    public CarDetails updateCar(Long id, CarDetails car) {
        CarDetails existingCar = getCarById(id);

        existingCar.setCarModel(car.getCarModel());
        existingCar.setBrand(car.getBrand());
        existingCar.setPlateNumber(car.getPlateNumber());
        existingCar.setFuelType(car.getFuelType());
        existingCar.setTransmission(car.getTransmission());
        existingCar.setCategory(car.getCategory());
        existingCar.setSeatingCapacity(car.getSeatingCapacity());
        existingCar.setDailyRate(car.getDailyRate());
        existingCar.setStatus(car.getStatus());
        existingCar.setYear(car.getYear());
        existingCar.setDescription(car.getDescription());
        existingCar.setImageUrl(car.getImageUrl());
        existingCar.setCurrentLat(car.getCurrentLat());
        existingCar.setCurrentLng(car.getCurrentLng());

        return carRepository.save(existingCar);
    }

    @Override
    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete. Car not found.");
        }
        carRepository.deleteById(id);
    }

    @Override
    public List<CarDetails> getCarsByStatus(String status) {
        return carRepository.findByStatus(status);
    }

    @Override
    public List<CarDetails> getCarsByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    @Override
    public List<CarDetails> searchByModel(String model) {
        return carRepository.findByCarModelContainingIgnoreCase(model);
    }
}
