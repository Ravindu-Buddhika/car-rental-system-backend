package org.example.repository;

import org.example.model.entity.CarDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<CarDetails, Long> {

    // Advance Filter Query
    @Query("SELECT c FROM CarDetails c WHERE " +
            "(:brandId IS NULL OR c.brand.carId = :brandId) AND " +
            "(:categoryId IS NULL OR c.category.carId = :categoryId) AND " +
            "(:fuelTypeId IS NULL OR c.fuelType.carId = :fuelTypeId) AND " +
            "(:transmissionId IS NULL OR c.transmission.carId = :transmissionId) AND " +
            "(:capacityId IS NULL OR c.seatingCapacity.carId = :capacityId) AND " +
            "(:status IS NULL OR c.status = :status)")
    List<CarDetails> findCarsByFilters(
            @Param("brandId") Long brandId,
            @Param("categoryId") Long categoryId,
            @Param("fuelTypeId") Long fuelTypeId,
            @Param("transmissionId") Long transmissionId,
            @Param("capacityId") Long capacityId,
            @Param("status") String status);

    List<CarDetails> findByCarModelContainingIgnoreCase(String model);
}