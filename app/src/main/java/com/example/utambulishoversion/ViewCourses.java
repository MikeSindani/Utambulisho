package com.example.utambulishoversion;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.widget.TextView;

import com.example.utambulishoversion.Adapter.CourseRVAdapter;
import com.example.utambulishoversion.modal.CourseModal;
import com.example.utambulishoversion.database.*;

import java.util.ArrayList;

public class ViewCourses extends AppCompatActivity {

    private ImageView btnViewinterroBack,btnViewinterroLogo,btnViewinterroScan;
    private TextView btnViewInterrotxt ;

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<CourseModal> courseModalArrayList;
    private DBHandler dbHandler;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);

        btnViewinterroBack=findViewById(R.id.btn_viewcourse_back);
        btnViewinterroLogo=findViewById(R.id.btn_viewcourse_logo);
        btnViewinterroScan=findViewById(R.id.btn_viewcourse_scan);
        btnViewInterrotxt=findViewById(R.id.txt_viewcourse_text);

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
                Intent i = new Intent(getApplicationContext(),ViewCourses.class);
                startActivity(i);
            }
        });

        btnViewinterroScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),InterroContactActivity.class);
                startActivity(i);
            }
        });

        // initializing our all variables.
        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewCourses.this);

        // getting our course array
        // list from db handler class.
        courseModalArrayList = dbHandler.readCourses();

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new CourseRVAdapter(courseModalArrayList, ViewCourses.this);
        coursesRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewCourses.this, RecyclerView.VERTICAL, true);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);
    }
}
