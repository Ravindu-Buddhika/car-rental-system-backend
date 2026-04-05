package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.CarDetails;
import org.example.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
@CrossOrigin
public class CarController {

    private final CarService carService;

    @PostMapping("/save")
    public ResponseEntity<CarDetails> saveCar(@RequestBody CarDetails car) {
        return ResponseEntity.ok(carService.saveCar(car));
    }
    @GetMapping("/all")
    public ResponseEntity<List<CarDetails>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }
}