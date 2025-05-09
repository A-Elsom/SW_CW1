package com.example.part2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    Long insert(Student student);

    @Query("SELECT * FROM students ORDER BY studentId DESC")
    LiveData<List<Student>> getStudentList();

    @Delete
    void delete(Student student);

    @Query("SELECT * FROM students WHERE studentId = :Id")
    LiveData<Student> getStudentById(int Id);

}
