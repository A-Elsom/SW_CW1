package com.example.part2;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.Console;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Course.class, Student.class, StudentCourses.class}, version = 1, exportSchema = false)
public abstract class UniversityDB extends RoomDatabase {
    public abstract CourseDao courseDao();
    public abstract StudentDao studentDao();

    public abstract StudentCoursesDao studentCoursesDao();

    private static volatile UniversityDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    private static Context contextT;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                CourseDao courseDao = INSTANCE.courseDao();
                courseDao.deleteAll();
                Course course = new Course("CO2124", "SoftArch", "Someone");
                courseDao.insert(course);
                Toast.makeText(contextT, "added Course", Toast.LENGTH_SHORT).show();
            });
        }
    };
    static UniversityDB getDatabase(final Context context){
        contextT = context;
        if(INSTANCE == null){
            synchronized (UniversityDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UniversityDB.class, "uni_DB").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }


}
