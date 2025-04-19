package com.example.part1.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor {

    @Id
    private Long id;
    private String name;
    private String Specialisation;
    private String eMail;
    private String phoneNumber;
    @OneToMany(orphanRemoval= true, cascade = CascadeType.ALL)
    private List<Appointments> appointmentsList = new ArrayList<>();


    //Setters
    public void setId(long id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setSpecialisation(String specialisation) {this.Specialisation = specialisation;}

    public void seteMail(String eMail) {this.eMail = eMail;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    //Getters
    public Long getId() {return id;}

    public int getIdAsInt(){return Math.toIntExact(id);}

    public String getName() {return name;}

    public String getSpecialisation() {return Specialisation;}

    public String geteMail() {return eMail;}

    public String getPhoneNumber() {return phoneNumber;}

    public List<Appointments> getAppointmentsList() {return appointmentsList;}
}
