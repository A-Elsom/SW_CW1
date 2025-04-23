package com.example.part2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface StudentCoursesDao {
    @Insert
    void insert(StudentCourses newEntry);

    @Delete
    void delete(StudentCourses delEntry);

    @Query("SELECT * FROM student_courses")
    LiveData<List<StudentCourses>> getStudentCoursesList();

    @Transaction
    @Query("SELECT courses.code,courses.lecturer_Name,courses.name FROM courses, student_courses WHERE courses.id = course_id  & student_id = :studentId")
    LiveData<List<StudentCourses>> getStudentsEnrolledCourses(long studentId);

    @Transaction
    @Query("SELECT students.name, students.username FROM students, student_courses WHERE students.id = student_id  & course_id = :courseId")
    LiveData<List<StudentCourses>> getCourseEnrolledStudents(long courseId);
}
