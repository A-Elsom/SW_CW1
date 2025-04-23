package com.example.part2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Query;

@Entity(tableName = "student_courses", primaryKeys = {"student_id", "course_id"})
public class StudentCourses {

    //@ColumnInfo(name = "student_id")
    private long student_id;

    //@ColumnInfo(name = "course_id")
    private long course_id;
}
