package com.example.part2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "courses")
public class Course {


    @PrimaryKey(autoGenerate = true)
    private int courseId;
    @ColumnInfo(name = "code")
    private String courseCode;

    @ColumnInfo(name = "name")
    private String courseName;

    @ColumnInfo(name = "lecturer_Name")
    private String lecturerName;

    public Course(String courseCode, String courseName, String lecturerName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.lecturerName = lecturerName;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

}
