package com.example.part2;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class CourseRepository {
    private CourseDao courseDao;

    private LiveData<List<Course>> allCourses;

    CourseRepository(Application application){
        UniversityDB db = UniversityDB.getDatabase(application);
        courseDao = db.courseDao();
        allCourses = courseDao.getCourseList();
    }

    LiveData<List<Course>> getAllCourses(){return allCourses;}

    LiveData<Course> getCourseById(int id) {return courseDao.getCourseById(id);}

    void insert(Course course){
        UniversityDB.databaseWriteExecutor.execute(() -> {
            courseDao.insert(course);
        });
    }

    public void delete(Course course){
        UniversityDB.databaseWriteExecutor.execute(() -> {
            courseDao.delete(course);
        });
    }

    void deleteAll(){
        UniversityDB.databaseWriteExecutor.execute(() -> {
            courseDao.deleteAll();
        });
    }
}
