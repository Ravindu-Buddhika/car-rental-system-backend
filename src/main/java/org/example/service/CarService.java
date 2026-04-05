package org.example.service;

import org.example.model.entity.CarDetails;

import java.util.List;

public interface CarService {
    CarDetails saveCar(CarDetails car);
    List<CarDetails> getAllCars();
    CarDetails getCarById(Long id);

    CarDetails updateCar(Long id, CarDetails car);
}
