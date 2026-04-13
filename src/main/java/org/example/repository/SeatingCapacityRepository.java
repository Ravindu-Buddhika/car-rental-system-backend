package org.example.repository;

import org.example.model.entity.SeatingCapacity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatingCapacityRepository extends JpaRepository<SeatingCapacity,Long> {
}
