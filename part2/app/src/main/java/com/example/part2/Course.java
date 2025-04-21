package com.example.part2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String courseCode;

    private String courseName;

    private String lecturerName;
}
