package com.example.part2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private CourseRepository repo;
    private final LiveData<List<Course>> courses;

    public CourseViewModel(Application application){
        super(application);
        repo = new CourseRepository(application);
        courses = repo.getAllCourses();
    }

    LiveData<List<Course>> getAllCourses() {return courses;}

    public void insert(Course course) {repo.insert(course);}

    public void delete(Course course) {repo.delete(course);}

    public void deleteAll() {repo.deleteAll();}

    public LiveData<Course> getCourseById(int id) {return repo.getCourseById(id);}
}
