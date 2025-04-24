package com.example.part2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

public class CourseDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance){
        //load course view
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_course_details);
        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        Log.println(Log.INFO, "test", String.valueOf(getIntent().getExtras().getInt("thisCourse")));
        LiveData<Course> boundCourse = courseViewModel.getCourseById(getIntent().getExtras().getInt("thisCourse"));

    }
}
