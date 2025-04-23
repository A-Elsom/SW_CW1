package com.example.part2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "code")
    private String courseCode;

    @ColumnInfo(name = "name")
    private String courseName;

    @ColumnInfo(name = "lecturer_Name")
    private String lecturerName;

}
