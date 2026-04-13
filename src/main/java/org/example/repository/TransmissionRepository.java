package org.example.repository;

import org.example.model.entity.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionRepository extends JpaRepository<Transmission,Long> {
}
