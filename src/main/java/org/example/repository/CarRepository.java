package org.example.repository;

import org.example.model.entity.CarDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<CarDetails,Long> {
    List<CarDetails> findByStatus(String status);
}
