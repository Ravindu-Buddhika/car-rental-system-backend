package org.example.repository;

import org.example.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByNic(String nic);
    Optional<Customer> findByUser_UserId(Long userId);
    Optional<Customer> findByEmail(String email);
}
