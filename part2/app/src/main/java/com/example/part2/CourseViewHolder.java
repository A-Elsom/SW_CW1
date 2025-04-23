package com.example.part2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CourseViewHolder extends RecyclerView.ViewHolder {
    private final TextView courseTextView;

    private CourseViewHolder(View CourseView){
        super(CourseView);
        courseTextView = CourseView.findViewById(R.id.textView);
    }

    public void bind(String text){
        courseTextView.setText(text);
    }

    static CourseViewHolder Create(ViewGroup Parent){
        View view = LayoutInflater.from(Parent.getContext()).inflate(R.layout.recyclerview_course, Parent, false);
        return new CourseViewHolder(view);
    }
}
