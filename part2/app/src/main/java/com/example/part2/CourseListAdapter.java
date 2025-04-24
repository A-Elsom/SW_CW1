package com.example.part2;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import org.jetbrains.annotations.NotNull;

public class CourseListAdapter extends ListAdapter<Course, CourseViewHolder> {
    public interface LongClickListener{
        void onLongClick(Course course);
    }

    private final LongClickListener longClickListener;
    public CourseListAdapter(@NotNull DiffUtil.ItemCallback<Course> diffCallback, LongClickListener longClickListener){
        super(diffCallback);
        this.longClickListener = longClickListener;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return CourseViewHolder.Create(parent);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position){
        Course current = getItem(position);
        holder.bindId(current.getCourseId());
        holder.bind("|Course Code : " + current.getCourseCode() + "| |Course Name : " + current.getCourseName() + "| |Lecturer : " + current.getLecturerName());

        holder.getCoursebutton().setOnLongClickListener(v -> {
            longClickListener.onLongClick(current);
            return true;
        });
    }

    static class CourseDiff extends DiffUtil.ItemCallback<Course>{
        @Override
        public boolean areItemsTheSame(@NonNull Course oldCourse, @NonNull Course newCourse){
            return oldCourse == newCourse;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Course oldCourse, @NonNull Course newCourse){
            return oldCourse.getCourseId() == (newCourse.getCourseId());
        }
    }
}
