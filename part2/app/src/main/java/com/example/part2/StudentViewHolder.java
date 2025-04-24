package com.example.part2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    private final Button studentbutton;

    private StudentViewHolder(View CourseView){
        super(CourseView);
        studentbutton = CourseView.findViewById(R.id.studentButton);
    }

    public void bind(String text){
        studentbutton.setText(text);
    }

    public void bindId(int id) {studentbutton.setId(id);}

    static StudentViewHolder Create(ViewGroup Parent){
        View view = LayoutInflater.from(Parent.getContext()).inflate(R.layout.recyclerview_student, Parent, false);
        return new StudentViewHolder(view);
    }
}
