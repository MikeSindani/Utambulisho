package com.example.utambulishoversion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utambulishoversion.Adapter.CourseExamRVAdapter;
import com.example.utambulishoversion.modal.CourseModal;
import com.example.utambulishoversion.database.*;

import java.util.ArrayList;

public class ViewCoursesExam extends AppCompatActivity {


    private ImageView btnViewinterroBack,btnViewinterroLogo,btnViewinterroScan;
    private TextView btnViewInterrotxt ;

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<CourseModal> courseModalArrayList;
    private DBHandlerExam dbHandler;
    private CourseExamRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses_exam);

        btnViewinterroBack=findViewById(R.id.btn_viewcourseexam_back);
        btnViewinterroLogo=findViewById(R.id.btn_viewcourseexam_logo);
        btnViewinterroScan=findViewById(R.id.btn_viewcourseexam_scan);
        btnViewInterrotxt=findViewById(R.id.txt_viewcourseexam_text);

        btnViewinterroBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
            }
        });
        btnViewinterroLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
            }
        });
        btnViewInterrotxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ViewCoursesExam.class);
                startActivity(i);
            }
        });

        btnViewinterroScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ExamContactActivity.class);
                startActivity(i);
            }
        });

        // initializing our all variables.
        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandlerExam(getApplicationContext());

        // getting our course array
        // list from db handler class.
        courseModalArrayList = dbHandler.readCourses();

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new CourseExamRVAdapter(courseModalArrayList, getApplicationContext());
        coursesRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, true);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);
    }


}