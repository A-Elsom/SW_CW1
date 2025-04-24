package com.example.part2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentCoursesRepository {
    private StudentCoursesDao studentCoursesDao;
    private LiveData<List<StudentCourses>> allStudentCourses;

    StudentCoursesRepository(Application application){
        UniversityDB uniDB = UniversityDB.getDatabase(application);
        studentCoursesDao = uniDB.studentCoursesDao();
        allStudentCourses = studentCoursesDao.getStudentCoursesList();
    }

    LiveData<List<StudentCourses>> getAllStudentCourses(){
        return allStudentCourses;
    }

    void insert(StudentCourses studentCourse){
        UniversityDB.databaseWriteExecutor.execute(() -> {
            studentCoursesDao.insert(studentCourse);
        });
    }

    void delete(StudentCourses studentCourse){
        UniversityDB.databaseWriteExecutor.execute(() -> {
            studentCoursesDao.delete(studentCourse);
        });
    }

   LiveData<List<Course>> getStudentsEnrolledCourses(int studentId){
        return studentCoursesDao.getStudentsEnrolledCourses(studentId);
    }

    LiveData<List<Student>> getCourseEnrolledStudents(int courseId){
        return studentCoursesDao.getCourseEnrolledStudents(courseId);
    }

    void deleteByCourses(int courseId){
        UniversityDB.databaseWriteExecutor.execute(() -> {
            studentCoursesDao.deleteByCourses(courseId);
        });
    }
}
