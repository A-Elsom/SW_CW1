package com.example.part2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Query;

@Entity(tableName = "student_courses", primaryKeys = {"student_id", "course_id"})
public class StudentCourses {


    @ColumnInfo(name = "student_id")
    private long student_id;
    @ColumnInfo(name = "course_id")
    private long course_id;


    public long getCourse_id() {
        return course_id;
    }

    public long getStudent_id() {
        return student_id;
    }

    public StudentCourses(long student_id, long course_id) {
        this.student_id = student_id;
        this.course_id = course_id;
    }
}

