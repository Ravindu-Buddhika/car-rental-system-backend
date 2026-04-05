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
    @GetMapping("/{id}")
    public ResponseEntity<CarDetails> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CarDetails> updateCar(@PathVariable Long id, @RequestBody CarDetails car) {
        return ResponseEntity.ok(carService.updateCar(id, car));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok("Car deleted successfully!");
    }
    @GetMapping("/filter/status")
    public ResponseEntity<List<CarDetails>> getByStatus(@RequestParam String status) {
        return ResponseEntity.ok(carService.getCarsByStatus(status));
    }
    @GetMapping("/filter/brand")
    public ResponseEntity<List<CarDetails>> getByBrand(@RequestParam String brand) {
        return ResponseEntity.ok(carService.getCarsByBrand(brand));
    }
    @GetMapping("/search/model")
    public ResponseEntity<List<CarDetails>> searchByModel(@RequestParam String model) {
        return ResponseEntity.ok(carService.searchByModel(model));
    }
    @GetMapping("/search/model")
    public ResponseEntity<List<CarDetails>> searchByCategory(@RequestParam String category) {
        return ResponseEntity.ok(carService.getCarsByCategory(category));
    }
    @GetMapping("/filter/transmission")
    public ResponseEntity<List<CarDetails>> getByTransmission(@RequestParam String transmission) {
        return ResponseEntity.ok(carService.getCarsByTransmission(transmission));
    }
}