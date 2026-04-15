package org.example.repository;

import org.example.model.entity.CarDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<CarDetails, Long> {

    // Advance Filter Query
    @Query("SELECT c FROM CarDetails c WHERE " +
            "(:brandId IS NULL OR c.brand.id = :brandId) AND " + // මෙතන .id විය යුතුයි
            "(:categoryId IS NULL OR c.category.id = :categoryId) AND " +
            "(:fuelTypeId IS NULL OR c.fuelType.id = :fuelTypeId) AND " +
            "(:transmissionId IS NULL OR c.transmission.id = :transmissionId) AND " +
            "(:capacityId IS NULL OR c.seatingCapacity.id = :capacityId) AND " +
            "(:status IS NULL OR c.status = :status)")
    List<CarDetails> findCarsByFilters(
            @Param("brandId") Long brandId,
            @Param("categoryId") Long categoryId,
            @Param("fuelTypeId") Long fuelTypeId,
            @Param("transmissionId") Long transmissionId,
            @Param("capacityId") Long capacityId,
            @Param("status") String status
    );

    List<CarDetails> findByCarModelContainingIgnoreCase(String model);
}