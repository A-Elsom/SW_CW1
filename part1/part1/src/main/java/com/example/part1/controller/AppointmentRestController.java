package com.example.part1.controller;

import com.example.part1.domain.Appointments;
import com.example.part1.domain.Doctor;
import com.example.part1.domain.Record;
import com.example.part1.repo.AppointmentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class AppointmentRestController {

    @Autowired
    private AppointmentRepo repo;

    //List All Appointments (endpoint #14)
    @GetMapping("/appointments")
    public ResponseEntity<?> listAppointments(@RequestBody Appointments appointments){
        List<Appointments> appointmentsList = repo.findAll();
        if(appointmentsList.isEmpty()){
            return new ResponseEntity<List<Appointments>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Appointments>>(appointmentsList, HttpStatus.OK);
    }

    //create a new Appointment (endpoint #15)
    @PostMapping("/appointments")
    public ResponseEntity<?> createAppointment(@RequestBody Appointments appointments, UriComponentsBuilder ucBuilder){

        if(repo.existsById(appointments.getId())){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        repo.save(appointments);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/appointments/{id}").buildAndExpand(appointments.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //retrieve a specific appointment by ID (endpoint #16)
    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointments> getAppointmentById(@PathVariable("Id") long Id){
        Appointments appointment = repo.findById(Id).orElse(null);
        if(appointment == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        repo.delete(appointment);
        return new ResponseEntity<Appointments>(appointment, HttpStatus.OK);
    }

    //update a specific appointment by ID (endpoint #17)
    @PutMapping("/appointments/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable("id") Long id, @RequestBody Appointments appointment){
        Appointments currentAppointment = repo.findById(id).orElse(null);
        if (currentAppointment == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        currentAppointment.setAppointmentDate(appointment.getAppointmentDate());
        currentAppointment.setDoctor(appointment.getDoctor());
        currentAppointment.setPatient(appointment.getPatient());
        currentAppointment.setNotes(appointment.getNotes());
        currentAppointment.setStatus(appointment.getStatus());
        repo.save(currentAppointment);
        return new ResponseEntity<Appointments>(currentAppointment, HttpStatus.OK);
    }

    //delete a specific appointment by ID (endpoint #18)
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("Id") long id){
        Appointments removeAppointment = repo.findById(id).orElse(null);
        if(removeAppointment == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        repo.delete(removeAppointment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/appointments/{id}/medical-record")
    public ResponseEntity<?> getMedicalRecord(@PathVariable("id") long id) {
        Appointments appointment = repo.findById(id).orElse(null);
        if(appointment == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Record record = appointment.getRecord();
        return new ResponseEntity<Record>(record, HttpStatus.OK);
    }
}
