package com.example.part2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StudentDetailsActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstance){

        //load course view
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_student_details);
        //retieves the TextViews in frontend
        TextView studentNameView = (TextView)findViewById(R.id.studentName);
        TextView studentEmailView = (TextView)findViewById(R.id.studentEmail);
        TextView matricNumberView = (TextView)findViewById(R.id.matricNumber);

        //gets the courseViewModel
        StudentViewModel studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        StudentCoursesViewModel studentCoursesViewModel = new ViewModelProvider(this).get(StudentCoursesViewModel.class);
        //debug
            Log.println(Log.INFO, "test", String.valueOf(getIntent().getExtras().getInt("thisStudent")));
        //Log.println(Log.INFO, "test", String.valueOf(studentViewModel..getValue()));
        //updates the ui
            studentViewModel.getStudentById(getIntent().getExtras().getInt("thisStudent")).observe(this,boundStudent -> {
            String nText = "Name : " + boundStudent.getName();
            studentNameView.setText(boundStudent.getName());
            studentEmailView.setText(boundStudent.getEmail());
            matricNumberView.setText(boundStudent.getUserName());
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview_courses);
        CourseListAdapter adapter = new CourseListAdapter(new CourseListAdapter.CourseDiff(), course -> {courseViewModel.delete(course); Toast.makeText(this, "Course deleted: " + course.getCourseName(), Toast.LENGTH_SHORT).show();});
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            studentCoursesViewModel.getCoursesForStudent(getIntent().getExtras().getInt("thisStudent")).observe(this, courses -> {
            Log.println(Log.INFO, "numOfStudents", String.valueOf(courses.size()));
            adapter.submitList(courses);
        });
    }

    public void onCloseEvent(View view){
        finish();
    }
}
