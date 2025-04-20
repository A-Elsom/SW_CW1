package com.example.part1.controller;

import com.example.part1.domain.Appointments;
import com.example.part1.domain.Doctor;
import com.example.part1.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
public class DoctorRestController {

    @Autowired
    private DoctorRepo repo;

    //endpoint #8, Get list of all doctors
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> listAllDoctors(){
        List<Doctor> doctors = repo.findAll();
            if (doctors.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
    }

    //endpoint 9, Create new doctor
    @PostMapping("/doctors")
    public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor, UriComponentsBuilder ucBuilder){

        if(repo.existsById(doctor.getId())){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        repo.save(doctor);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //endpoint 10, retrieve specific endpoint by ID
    @GetMapping("/doctors/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id){
        Doctor doctor = repo.findById(id).orElse(null);
        if (doctor == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
    }

    //endpoint 11, Update a specific doctor by ID
    @PutMapping("/doctors/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable("id") Long id, @RequestBody Doctor doctor){
        Doctor currentDoc = repo.findById(id).orElse(null);
        if (currentDoc == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        currentDoc.setName(doctor.getName());
        currentDoc.setSpecialisation(doctor.getSpecialisation());
        currentDoc.seteMail(doctor.geteMail());
        currentDoc.setPhoneNumber(doctor.getPhoneNumber());
        repo.save(currentDoc);
        return new ResponseEntity<Doctor>(currentDoc, HttpStatus.OK);
    }

    //endpoint 12, Delete specific doctor by ID
    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable("id") Long id){
        Doctor doctor = repo.findById(id).orElse(null);
        if (doctor == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        repo.delete(doctor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //endpoint 13, getting a List of all appointments for a specific doctor
    @GetMapping("/doctors/{id}/appointments")
    public ResponseEntity<List<Appointments>> listAppointments(@PathVariable("id") Long id){
        Doctor doctor = repo.findById(id).orElse(null);
        if (doctor == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<Appointments> docAppointments = doctor.getAppointmentsList();
        if (docAppointments.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Appointments>>(docAppointments, HttpStatus.OK);
    }

}
