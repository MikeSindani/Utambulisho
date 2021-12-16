package com.example.utambulishoversion;


import static jxl.Workbook.createWorkbook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.utambulishoversion.database.*;
import com.example.utambulishoversion.Adapter.*;
import com.example.utambulishoversion.modal.QrscanInterroModal;

import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ViewInterroActivity extends AppCompatActivity {
    private ImageView btnViewinterroBack,btnViewinterroLogo,btnViewinterroScan;
    private TextView btnViewInterrotxt ;

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<QrscanInterroModal> QrscanInterroModalArrayList;
    private DBscanInterro dbHandler;
    private ScanInterroRvAdapter ScanInterroRvAdapter ;
    private RecyclerView coursesRV;


    ImageView btnexport;
    File directory, sd, file;
    WritableWorkbook workbook;
    private EditText editTextExcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_interro);

        btnViewinterroBack=findViewById(R.id.btn_viewinterro_back);
        btnViewinterroLogo=findViewById(R.id.btn_viewinterro_logo);
        btnViewinterroScan=findViewById(R.id.btn_viewinterro_scan);
        btnViewInterrotxt=findViewById(R.id.txt_viewinterro_text);

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


        btnViewinterroScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),InterroContactActivity.class);
                startActivity(i);
            }
        });
        // initializing our all variables.
        QrscanInterroModalArrayList = new ArrayList<>();
        dbHandler = new DBscanInterro(getApplicationContext());
        // permition
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        // create the get Intent object
        Intent intent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        String courseDescription = intent.getStringExtra("promotion1");
        String courseDuration = intent.getStringExtra("cours1");
        String courseTracks = intent.getStringExtra("session1");
        String courseName = intent.getStringExtra("nom1");

        // getting our course array
        // list from db handler class.
        QrscanInterroModalArrayList = dbHandler.readElementInterro(courseName,courseDescription,courseTracks);

        // on below line passing our array lost to our adapter class.
        ScanInterroRvAdapter  = new ScanInterroRvAdapter(QrscanInterroModalArrayList, getApplicationContext());
        coursesRV = findViewById(R.id.idRVQrscanInterro);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(ScanInterroRvAdapter);
        // exporter au fromat excel
        btnexport = findViewById(R.id.btn_xls_export_interro);
        btnexport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String csvFile = courseName+" "+courseDescription+" "+courseTracks+".xls";
                sd = Environment.getExternalStorageDirectory();
                directory = new File(sd.getAbsolutePath());
                file = new File(directory, csvFile);


                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setLocale(new Locale("fr", "FR"));
                Toast.makeText(getApplicationContext(),csvFile+"a ete creer", Toast.LENGTH_SHORT).show();
                try {
                    workbook = createWorkbook(file, wbSettings);
                    createFirstSheet(courseName,courseDescription,courseTracks);
                    //closing cursor
                    workbook.write();
                    workbook.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    void createFirstSheet(String courseName,String courseDescription, String courseDuration) {
        try {

            List<QrscanInterroModal> listdata = new ArrayList<>();
            // list from db handler class.
            listdata= dbHandler.readElementInterro(courseName,courseDescription,courseDuration);
            //listdata.add(new QrscanInterroModal("mr","firstName1","middleName1","lastName1"));
            //listdata.add(new QrscanInterroModal("mr","firstName1","middleName1","lastName1"));
            //listdata.add(new QrscanInterroModal("mr","firstName1","middleName1","lastName1"));
            //Excel sheet name. 0 (number)represents first sheet
            WritableSheet sheet = workbook.createSheet("sheet1", 0);
            // column and row title
            sheet.addCell(new Label(0, 0, "scan"));
            sheet.addCell(new Label(1, 0, "Nom du surveillant"));
            sheet.addCell(new Label(2, 0, "cours"));
            sheet.addCell(new Label(3, 0, "promotion"));



            for (int i = 0; i < listdata.size(); i++) {
                QrscanInterroModal modal = listdata.get(i);
                sheet.addCell(new Label(0, i + 1, modal.getCourseName()));
                sheet.addCell(new Label(1, i + 1, modal.getCourseTracks()));
                sheet.addCell(new Label(2, i + 1, modal.getCourseDuration()));
                sheet.addCell(new Label(3, i + 1, modal.getCourseDescription()));

                Toast.makeText(getApplicationContext(),"Fichier excel a ete creer", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
