package com.example.part2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Query;

@Entity(tableName = "student_courses", primaryKeys = {"student_id", "course_id"})
public class StudentCourses {


    @ColumnInfo(name = "student_id")
    private int student_id;
    @ColumnInfo(name = "course_id")
    private int course_id;


    public int getCourse_id() {
        return course_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public StudentCourses(int student_id, int course_id) {
        this.student_id = student_id;
        this.course_id = course_id;
    }
}

