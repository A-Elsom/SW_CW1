package com.example.part2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {
    public static final String EXTRA_STUDENT_NAME = "com.example.part2.STUDENT_NAME";
    public static final String EXTRA_STUDENT_EMAIL = "com.example.part2.STUDENT_EMAIL";
    public static final String EXTRA_STUDENT_MATRIC_NO = "com.example.part2.STUDENT_MATRIC_NO";

    private EditText editStudentName;
    private EditText editStudentEmail;
    private EditText editStudentMatricNo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        editStudentName = findViewById(R.id.edit_student_name);
        editStudentEmail = findViewById(R.id.edit_student_email);
        editStudentMatricNo = findViewById(R.id.edit_student_matric_no);
        final Button addStudentButton = findViewById(R.id.add_student_button);
        addStudentButton.setOnClickListener(view ->{
            Intent replyIntent = new Intent();
            if(TextUtils.isEmpty(editStudentName.getText()) || TextUtils.isEmpty(editStudentEmail.getText()) || TextUtils.isEmpty(editStudentMatricNo.getText())){
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra(EXTRA_STUDENT_NAME, editStudentName.getText().toString());
                replyIntent.putExtra(EXTRA_STUDENT_EMAIL, editStudentEmail.getText().toString());
                replyIntent.putExtra(EXTRA_STUDENT_MATRIC_NO, editStudentMatricNo.getText().toString());
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
