package com.example.part2;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface StudentDao {
    @Insert
    void insert(Student student);
}
