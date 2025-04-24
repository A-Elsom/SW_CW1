package com.example.part2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "students")
public class Student {




    @PrimaryKey(autoGenerate = true)
    private int studentId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "userName")
    private String UserName;

    public Student(String name, String email, String UserName) {
        this.name = name;
        this.email = email;
        this.UserName = UserName;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return UserName;
    }

}
