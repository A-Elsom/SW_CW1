package com.example.part1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Record {
    @Id
    private Long id;
}
