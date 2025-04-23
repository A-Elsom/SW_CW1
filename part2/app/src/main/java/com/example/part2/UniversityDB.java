package com.example.part2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Course.class, Student.class, StudentCourses.class}, version = 1, exportSchema = false)
public abstract class UniversityDB extends RoomDatabase {
    public abstract CourseDao courseDao();
    public abstract StudentDao studentDao();

    public abstract StudentCoursesDao studentCoursesDao();

    private static volatile UniversityDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static UniversityDB getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (UniversityDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UniversityDB.class, "uni_DB").build();
                }
            }
        }
        return INSTANCE;
    }


}
