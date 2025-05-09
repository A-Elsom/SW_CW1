package com.example.part2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    public static final int CREATE_COURSE_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        setSupportActionBar(binding.toolbar);

//        NavController navController = Navigation.findNavController(this, R.id.recyclerview);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
//            }
//        });

        //CreateCourse fab action listener
        FloatingActionButton CCfab = findViewById(R.id.CCfab);
        CCfab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateCourseActivity.class);
            startActivityForResult(intent, CREATE_COURSE_REQUEST_CODE);
                });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        StudentCoursesViewModel studentCoursesViewModel = new ViewModelProvider(this).get(StudentCoursesViewModel.class);
        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        final CourseListAdapter adapter = new CourseListAdapter(new CourseListAdapter.CourseDiff(), course -> {courseViewModel.delete(course); studentCoursesViewModel.deleteByCourses(course.getCourseId()); Toast.makeText(this, "Course deleted: " + course.getCourseName(), Toast.LENGTH_SHORT).show();});
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //courseViewModel.insert(new Course("co2124", "none", "somebody"));
        courseViewModel.getAllCourses().observe(this, courses -> {
            adapter.submitList(courses);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE_COURSE_REQUEST_CODE && resultCode == RESULT_OK){
            String courseCode = data.getStringExtra(CreateCourseActivity.EXTRA_COURSE_CODE);
            String courseName = data.getStringExtra(CreateCourseActivity.EXTRA_COURSE_NAME);
            String lecturerName = data.getStringExtra(CreateCourseActivity.EXTRA_LECTURER_NAME);

            Course course = new Course(courseCode, courseName, lecturerName);
            CourseViewModel vm = new ViewModelProvider(this).get(CourseViewModel.class);
            vm.insert(course);
        }  else {
            Toast.makeText(getApplicationContext(),"This action Failed due to an empty field", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void onClickEventCourse(View view){
        //go to course page
        Button b = (Button)view;
        //Toast.makeText(this, String.valueOf(b.getId()), Toast.LENGTH_SHORT).show();
        Intent courseIntent = new Intent(this, CourseDetailsActivity.class);
        courseIntent.putExtra("thisCourse", b.getId());
        startActivity(courseIntent);
    }
}