package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.request.CarRequestDTO;
import org.example.model.dto.response.CarResponseDTO;
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
    public ResponseEntity<CarResponseDTO> saveCar(@RequestBody CarRequestDTO carDTO) {
        return ResponseEntity.ok(carService.saveCar(carDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarResponseDTO>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    // මෙන්න අලුත් Advance Filter Endpoint එක
    @GetMapping("/filter")
    public ResponseEntity<List<CarResponseDTO>> getFilteredCars(
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long fuelTypeId,
            @RequestParam(required = false) Long transmissionId,
            @RequestParam(required = false) Long capacityId,
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(carService.getFilteredCars(brandId, categoryId, fuelTypeId, transmissionId, capacityId, status));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CarResponseDTO>> searchByModel(@RequestParam String model) {
        return ResponseEntity.ok(carService.searchByModel(model));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok("Car deleted successfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Long id, @RequestBody CarRequestDTO carDTO) {
        return ResponseEntity.ok(carService.updateCar(id, carDTO));
    }
}