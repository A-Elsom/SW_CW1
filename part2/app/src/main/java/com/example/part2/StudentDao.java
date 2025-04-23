package com.example.part2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insert(Student student);

    @Query("SELECT * FROM students ORDER BY id DESC")
    LiveData<List<Student>> getStudentList();

    @Query("DELETE FROM students")
    void deleteStudents();
}
