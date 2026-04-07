package org.example.repository;

import org.example.model.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental,Long> {
    List<Rental> findByCustomer_User_Email(String email);
}
