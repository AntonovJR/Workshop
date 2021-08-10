package com.example.workshop.repository;

import com.example.workshop.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByVinNumber(String vinNumber);
}
