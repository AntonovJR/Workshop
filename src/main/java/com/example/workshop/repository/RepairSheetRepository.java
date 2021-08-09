package com.example.workshop.repository;

import com.example.workshop.models.entities.RepairSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairSheetRepository extends JpaRepository<RepairSheet, Long> {
}
