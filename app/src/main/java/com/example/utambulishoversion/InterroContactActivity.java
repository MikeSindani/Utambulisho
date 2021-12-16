package com.example.utambulishoversion;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.utambulishoversion.Adapter.CourseRVAdapter;
import com.example.utambulishoversion.database.*;
import com.example.utambulishoversion.modal.CourseModal;

import java.util.ArrayList;

public class InterroContactActivity extends AppCompatActivity {
    // gerer les intents
    private ImageView btnViewinterroBack,btnViewinterroLogo,btnViewinterroScan;
    private TextView btnViewInterrotxt ;
    // creating variables for our edittext, button and dbhandler
    private EditText courseNameEdt,courseDurationEdt;
    private Spinner courseTracksEdt , courseDescriptionEdt;
    private Button readCourseBtn,scanBtn;
    private ImageView addCourseBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interro_contact);
        // menu de l'applis
        btnViewinterroBack=findViewById(R.id.btn_interrocontact_back);
        btnViewinterroLogo=findViewById(R.id.btn_interrocontact_logo);
        btnViewInterrotxt=findViewById(R.id.txt_interrocontact_text);

        btnViewinterroBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ViewCourses.class);
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
        // initializing all our variables.
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
        // is the spinner
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        // the button
        //readCourseBtn = findViewById(R.id.idBtnReadCourse);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);
        //scanBtn = findViewById(R.id.idBtnscan);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(getApplicationContext());

        // below line is to add on click listener for our add course button.
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String courseName = courseNameEdt.getText().toString();
                String courseTracks = String.valueOf(courseTracksEdt.getSelectedItem());
                String courseDuration = courseDurationEdt.getText().toString();
                String courseDescription = String.valueOf(courseDescriptionEdt.getSelectedItem());

                // validating if the text fields are empty or not.
                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseTracks);

                // after adding the data we are displaying a toast message.
                Toast.makeText(getApplicationContext(), "Dossier Creer", Toast.LENGTH_SHORT).show();

                // opening a new activity via a intent.
                Intent intent = new Intent(getApplicationContext(), QrscanActivity.class);

                // now by putExtra method put the value in key, value pair
                // key is message_key by this key we will receive the value, and put the string

                intent.putExtra("promotion", courseDescription);
                intent.putExtra("cours", courseDuration);
                intent.putExtra("session", courseTracks);
                intent.putExtra("nom", courseName);


                // start the Intent
                startActivity(intent);
            }
        });

        /*scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(getApplicationContext(), ViewQrscanInterroActivity.class);
                startActivity(i);
            }
        });
        readCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity.this, ViewCourses.class);
                startActivity(i);
            }
        });*/

    }
}
