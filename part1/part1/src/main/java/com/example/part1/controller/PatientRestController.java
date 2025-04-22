package com.example.part1.controller;

import com.example.part1.domain.Appointments;
import com.example.part1.domain.Patient;
import com.example.part1.domain.Record;
import com.example.part1.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientRestController {

    @Autowired
    private PatientRepo repo;

    //List All Patients (endpoint #1)
    @GetMapping("/patients")
    public ResponseEntity<?> listPatients(@RequestBody Patient patient){
        List<Patient> patients = repo.findAll();
        if(patients.isEmpty()){
            return new ResponseEntity<List<Patient>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
    }

    //create a new Patient (endpoint #2)
    @PostMapping("/patients")
    public ResponseEntity<?> createPatient(@RequestBody Patient patient, UriComponentsBuilder ucBuilder){

        if(repo.existsById(patient.getId())){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        repo.save(patient);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //retrieve a specific patient by ID (endpoint #3)
    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") long Id){
        Patient patient = repo.findById(Id).orElse(null);
        if(patient == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    //update a specific patient by ID (endpoint #4)
    @PutMapping("/patients/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient){
        Patient currentPatient = repo.findById(id).orElse(null);
        if (currentPatient == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        currentPatient.setName(patient.getName());
        currentPatient.setAddress(patient.getAddress());
        currentPatient.setPhoneNumber(patient.getPhoneNumber());
        currentPatient.setEmail(patient.getEmail());
        repo.save(currentPatient);
        return new ResponseEntity<Patient>(currentPatient, HttpStatus.OK);
    }

    //delete a specific patient by ID (endpoint #5)
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable("id") long id){
        Patient removePatient = repo.findById(id).orElse(null);
        if(removePatient == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        repo.DeleteById(id);
        if(!repo.existsById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    //List All appointments for a specific patient (Endpoint #6)
    @GetMapping("/patients/{id}/appointments")
    public ResponseEntity<?> getAppointments(@PathVariable("id") long id) {
        Patient patient = repo.findById(id).orElse(null);
        if(patient == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if(patient.getAppointmentList().isEmpty()){
            return new ResponseEntity<List<Appointments>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Appointments>>(patient.getAppointmentList(), HttpStatus.OK);
    }

    //List All Medical records for a specific patient (Endpoint #7)
    @GetMapping("/patients/{id}/medical-records")
    public ResponseEntity<?> getMedicalRecords(@PathVariable("id") long id) {
        Patient patient = repo.findById(id).orElse(null);
        if(patient == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        List<Appointments> appointments = patient.getAppointmentList();
        if(appointments.isEmpty()){
            return new ResponseEntity<List<Appointments>>(HttpStatus.NO_CONTENT);
        }
        List<Record> records = new ArrayList<>();
        for (int i = 0; i < appointments.size(); i++) {
            records.add(appointments.get(i).getRecord());
        }

        return new ResponseEntity<List<Record>>(records, HttpStatus.OK);
    }
}
