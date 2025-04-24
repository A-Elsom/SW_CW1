package com.example.part2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CourseViewHolder extends RecyclerView.ViewHolder {
    private final Button coursebutton;

    private CourseViewHolder(View CourseView){
        super(CourseView);
        coursebutton = CourseView.findViewById(R.id.courseButton);
    }

    public Button getCoursebutton() {
        return coursebutton;
    }

    public void bind(String text){
        coursebutton.setText(text);
    }

    public void bindId(int id) {coursebutton.setId(id);}

    static CourseViewHolder Create(ViewGroup Parent){
        View view = LayoutInflater.from(Parent.getContext()).inflate(R.layout.recyclerview_course, Parent, false);
        return new CourseViewHolder(view);
    }
}
