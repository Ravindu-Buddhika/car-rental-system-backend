package org.example.repository;

import org.example.model.entity.CarDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<CarDetails,Long> {
    List<CarDetails> findByStatus(String status);
    List<CarDetails> findByBrand(String brand);
    List<CarDetails> findByCarModelContainingIgnoreCase(String model);
    List<CarDetails> findByCategory(String category);
    List<CarDetails> findByTransmission(String transmission);
    List<CarDetails> findByFuelType(String fuelType);
    List<CarDetails> findBySeatingCapacity(Integer capacity);
}
