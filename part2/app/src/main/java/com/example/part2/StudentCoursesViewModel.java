package com.example.part2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentCoursesViewModel extends AndroidViewModel{

        private StudentCoursesRepository repo;
        private final LiveData<List<StudentCourses>> studentCourses;

        public StudentCoursesViewModel(Application application){
            super(application);
            repo = new StudentCoursesRepository(application);
            studentCourses = repo.getAllStudentCourses();
        }

        LiveData<List<StudentCourses>> getAllStudents() {return studentCourses;}

        public void insert(StudentCourses studentCourse) {repo.insert(studentCourse);}

        public void delete(StudentCourses studentCourse) {repo.delete(studentCourse);}
}

