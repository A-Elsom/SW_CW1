package com.example.part2;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class CourseDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstance){
        //load course view
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_course_details);
        //retieves the TextViews in frontend
        TextView CourseCodeView = (TextView)findViewById(R.id.courseCode);
        TextView CourseNameView = (TextView)findViewById(R.id.courseName);
        TextView CourseLecturerView = (TextView)findViewById(R.id.lecturer_name);

        //gets the courseViewModel
        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        StudentCoursesViewModel studentViewModel = new ViewModelProvider(this).get(StudentCoursesViewModel.class);
        //debug
        Log.println(Log.INFO, "test", String.valueOf(getIntent().getExtras().getInt("thisCourse")));
        Log.println(Log.INFO, "test", String.valueOf(courseViewModel.getAllCourses().getValue()));
        //Log.println(Log.INFO, "test", String.valueOf(studentViewModel..getValue()));
        //updates the ui
        courseViewModel.getCourseById(getIntent().getExtras().getInt("thisCourse")).observe(this,boundCourse -> {
            String nText = "Name : " + boundCourse.getCourseName();
            String lText = "Lecturer : " + boundCourse.getLecturerName();
            CourseCodeView.setText(boundCourse.getCourseCode());
            CourseNameView.setText(nText);
            CourseLecturerView.setText(lText);
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview_students);
        StudentListAdapter adapter = new StudentListAdapter(new StudentListAdapter.StudentDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentViewModel.getStudentsForCourses(getIntent().getExtras().getInt("thisCourse")).observe(this, students -> {
            Log.println(Log.INFO, "numOfStudents", String.valueOf(students.size()));
            adapter.submitList(students);
        });
    }

    public void onCloseEvent(){
        finish();
    }
}
