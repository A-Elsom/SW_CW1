package com.example.part2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private StudentRepository repo;
    private final LiveData<List<Student>> students;

    public StudentViewModel(Application application){
        super(application);
        repo = new StudentRepository(application);
        students = repo.getAllStudents();
    }

    LiveData<List<Student>> getAllStudents() {return students;}

    public LiveData<Student> getStudentById(int id) {return repo.getStudentById(id);}

    public void insert(Student student) {repo.insert(student);}

    public void delete(Student student) {repo.delete(student);}
}
