package com.example.part2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class CourseDetailsActivity extends AppCompatActivity {
    public static final int ADD_STUDENT_REQUEST_CODE = 1;

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

        FloatingActionButton asfab = findViewById(R.id.addStudentfab);
        asfab.setOnClickListener(view -> {
            Intent intent = new Intent(CourseDetailsActivity.this, AddStudentActivity.class);
            intent.putExtra("courseID", getIntent().getExtras().getInt("thisCourse"));
            startActivityForResult(intent, ADD_STUDENT_REQUEST_CODE);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_STUDENT_REQUEST_CODE && resultCode == RESULT_OK) {
            String studentName = data.getStringExtra(AddStudentActivity.EXTRA_STUDENT_NAME);
            String studentEmail = data.getStringExtra(AddStudentActivity.EXTRA_STUDENT_EMAIL);
            String studentMatricNo = data.getStringExtra(AddStudentActivity.EXTRA_STUDENT_MATRIC_NO);

            Student student = new Student(studentName, studentEmail, studentMatricNo);
            StudentViewModel studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
            studentViewModel.insertAndGetId(student).observe(this, studentId -> {
                if (studentId != null) {
                    student.setStudentId(studentId.intValue());

                    StudentCourses studentCourse = new StudentCourses(student.getStudentId(), getIntent().getExtras().getInt("thisCourse"));

                    StudentCoursesViewModel svm = new ViewModelProvider(CourseDetailsActivity.this).get(StudentCoursesViewModel.class);
                    svm.insert(studentCourse);
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "This action Failed due to an empty field", Toast.LENGTH_LONG).show();
        }
    }

    public void onCloseEvent(View view){
        finish();
    }

    public void onClickEventStudent(View view){
        //go to course page
        Button b = (Button)view;
        //Toast.makeText(this, String.valueOf(b.getId()), Toast.LENGTH_SHORT).show();
        Intent studentIntent = new Intent(this, StudentDetailsActivity.class);
        studentIntent.putExtra("thisStudent", b.getId());
        startActivity(studentIntent);
    }
}
