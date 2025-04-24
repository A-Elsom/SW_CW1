package com.example.part2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class EditStudentActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        TextInputEditText name = (TextInputEditText)findViewById(R.id.name);
        TextInputEditText email = (TextInputEditText) findViewById(R.id.email);
        TextInputEditText username = (TextInputEditText) findViewById(R.id.username);

        StudentViewModel studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
//        studentViewModel.getStudentById(getIntent().getExtras().getInt("StudentId")).observe(this, student -> {
//            name.setText(student.getName());
//            username.setText(student.getUserName());
//            email.setText(student.getEmail());
//        });

    }

    public void saveClicked(View view){
        TextInputEditText name = (TextInputEditText)findViewById(R.id.name);
        TextInputEditText email = (TextInputEditText) findViewById(R.id.email);
        TextInputEditText username = (TextInputEditText) findViewById(R.id.username);
        StudentViewModel studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        finish();
    }
}
