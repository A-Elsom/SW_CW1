package com.example.part2;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class StudentRepository {
    private StudentDao studentDao;
    private LiveData<List<Student>> allStudents;

    StudentRepository(Application application){
        UniversityDB uniDB = UniversityDB.getDatabase(application);
        studentDao = uniDB.studentDao();
        allStudents = studentDao.getStudentList();
    }

    LiveData<List<Student>> getAllStudents(){
        return allStudents;
    }
    LiveData<Student> getStudentById(int id) {return studentDao.getStudentById(id);}

    void insert(Student student){
        UniversityDB.databaseWriteExecutor.execute(() -> {
            studentDao.insert(student);
        });
    }

    public LiveData<Long> insertAndGetId(Student student) {
        final MutableLiveData<Long> insertedId = new MutableLiveData<>();
        UniversityDB.databaseWriteExecutor.execute(() -> {
            long id = studentDao.insert(student);
            insertedId.postValue(id);
        });
        return insertedId;
    }

    void delete(Student student){
        UniversityDB.databaseWriteExecutor.execute(() -> {
            studentDao.delete(student);
        });
    }

}
