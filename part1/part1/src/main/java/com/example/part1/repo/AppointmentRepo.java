package com.example.part1.repo;

import com.example.part1.domain.Appointments;
import com.example.part1.domain.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointments, Long> {

}
