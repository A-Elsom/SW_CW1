package com.example.part2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateCourseActivity extends AppCompatActivity {

    public static final String EXTRA_COURSE_CODE = "com.example.part2.COURSE_CODE";
    public static final String EXTRA_COURSE_NAME = "com.example.part2.COURSE_NAME";
    public static final String EXTRA_LECTURER_NAME = "com.example.part2.LECTURER_NAME";

    private EditText editCourseCode;
    private EditText editCourseName;
    private EditText editLecturerName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        editCourseCode = findViewById(R.id.edit_course_code);
        editCourseName = findViewById(R.id.edit_course_name);
        editLecturerName = findViewById(R.id.edit_lecturer_name);
        final Button courseButtonCreate = findViewById(R.id.course_button_create);
        courseButtonCreate.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if(TextUtils.isEmpty(editCourseCode.getText()) || TextUtils.isEmpty(editCourseName.getText()) || TextUtils.isEmpty(editLecturerName.getText())){
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra(EXTRA_COURSE_CODE, editCourseCode.getText().toString());
                replyIntent.putExtra(EXTRA_COURSE_NAME, editCourseName.getText().toString());
                replyIntent.putExtra(EXTRA_LECTURER_NAME, editLecturerName.getText().toString());
                setResult(RESULT_OK, replyIntent);
            }

            finish();
        });

    }
}
