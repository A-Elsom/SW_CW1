package com.example.part2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.MapColumn;
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
    @Query("SELECT courseId FROM courses, student_courses WHERE courses.courseId = course_id  & student_id = :studentId")
    List<Long> getStudentsEnrolledCourses(long studentId);

    @Transaction
    @Query("SELECT studentId FROM students, student_courses WHERE students.studentId = student_id  & course_id = :courseId")
    List<Long> getCourseEnrolledStudents(long courseId);
}
