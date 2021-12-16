package com.example.utambulishoversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    ImageView btnHomeInterro,btnHomeExamen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnHomeExamen= findViewById(R.id.btn_Home_Examen);
        btnHomeInterro=findViewById(R.id.btn_Home_interro);

        btnHomeInterro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ViewCourses.class);
                startActivity(i);
            }
        });
        btnHomeExamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ViewCoursesExam.class);
                startActivity(i);
            }
        });


    }
}