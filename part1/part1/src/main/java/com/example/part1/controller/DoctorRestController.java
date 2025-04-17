package com.example.part1.controller;

import com.example.part1.domain.Doctor;


@RestController
public class DoctorRestController {

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> listAllDoctors(){
        List<Doctor> doctors = repo.findAll();

        return new ResponseEntity<List<Hotel>>(doctors, HttpStatus.OK);
    }
}
