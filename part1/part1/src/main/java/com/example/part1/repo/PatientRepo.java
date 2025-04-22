package com.example.part1.repo;

import com.example.part1.domain.Patient;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM patient WHERE id = :id", nativeQuery = true)
    public void DeleteById(@Param("id") Long id);
}
