package com.example.part1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.part1.domain.Appointments;
import com.example.part1.domain.Record;
import com.example.part1.repo.AppointmentRepo;

@RestController
public class MedicalRecordRestController {
    @Autowired
    private AppointmentRepo repo;

    @PostMapping("/medical-records")
    public ResponseEntity<?> createMedicalRecord(@RequestBody Record record) {
        Long appointmentId = record.getAppointmentId();
        Appointments appointment = repo.findById(appointmentId).orElse(null);

        if (appointment == null) {
            return new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
        }

        appointment.setRecord(record);

        repo.save(appointment);

        return new ResponseEntity<>(record, HttpStatus.OK);
    }
}
