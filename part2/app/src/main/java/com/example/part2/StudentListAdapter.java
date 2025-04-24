package com.example.part2;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import org.jetbrains.annotations.NotNull;

public class StudentListAdapter extends ListAdapter<Student, StudentViewHolder> {

    public StudentListAdapter(@NotNull DiffUtil.ItemCallback<Student> diffCallback){
        super(diffCallback);
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return StudentViewHolder.Create(parent);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position){
        Student current = getItem(position);
        holder.bindId(current.getStudentId());
        holder.bind("Name : " + current.getName() + " Email : " + current.getEmail());
    }

    static class StudentDiff extends DiffUtil.ItemCallback<Student>{
        @Override
        public boolean areItemsTheSame(@NonNull Student oldStudent, @NonNull Student newStudent){
            return oldStudent == newStudent;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Student oldStudent, @NonNull Student newStudent){
            return oldStudent.getStudentId() == (newStudent.getStudentId());
        }
    }
}
