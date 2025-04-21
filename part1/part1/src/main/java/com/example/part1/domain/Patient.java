package com.example.part1.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Patient {
    @Id
    private long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    @OneToMany(orphanRemoval= true, cascade = CascadeType.ALL)
    private List<Appointments> appointmentList;

    public List<Appointments> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointments> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
