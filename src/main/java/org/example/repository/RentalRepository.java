package org.example.repository;

import org.example.model.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental,Long> {
    List<Rental> findByCustomer_User_Email(String email);
    @Query("SELECT SUM(r.totalAmount) FROM Rental r WHERE r.rentalStatus = 'COMPLETED'")
    Double getTotalRevenue();
    long countByRentalStatus(String status);
    long countByStartDate(LocalDate date);
    @Query("SELECT COUNT(r) FROM Rental r WHERE r.endDate < :today AND r.rentalStatus = 'Active'")
    long countOverdueRentals(LocalDate today);
    @Query("SELECT r FROM Rental r WHERE r.endDate < :today AND r.rentalStatus = 'Active'")
    List<Rental> findOverdueRentals(LocalDate today);
    List<Rental> findTop5ByOrderByRentalIdDesc();
}
