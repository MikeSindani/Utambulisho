package com.example.utambulishoversion;

import static jxl.Workbook.createWorkbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.utambulishoversion.Adapter.ScanExamenRvAdapter;
import com.example.utambulishoversion.Adapter.ScanInterroRvAdapter;
import com.example.utambulishoversion.database.DBscanExamen;
import com.example.utambulishoversion.database.DBscanInterro;
import com.example.utambulishoversion.modal.QrscanExamenModal;
import com.example.utambulishoversion.modal.QrscanInterroModal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ViewQrscanExamActivity extends AppCompatActivity {
    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<QrscanExamenModal> QrscanInterroModalArrayList;
    private DBscanExamen dbHandler;
    private com.example.utambulishoversion.Adapter.ScanExamenRvAdapter ScanInterroRvAdapter ;
    private RecyclerView coursesRV;
    ImageView btnexport;
    File directory, sd, file;
    WritableWorkbook workbook;
    private EditText editTextExcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_qrscan_exam);

        // initializing our all variables.
        QrscanInterroModalArrayList = new ArrayList<>();
        dbHandler = new DBscanExamen(getApplicationContext());
        // create the get Intent object
        Intent intent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        String courseDescription = intent.getStringExtra("promotion");
        String courseDuration = intent.getStringExtra("cours");
        String courseTracks = intent.getStringExtra("cours");
        String courseName = intent.getStringExtra("nom");

        // getting our course array
        // list from db handler class.
        QrscanInterroModalArrayList = dbHandler.readElementInterro(courseName,courseDescription,courseDuration);

        // on below line passing our array lost to our adapter class.
        ScanInterroRvAdapter  = new ScanExamenRvAdapter(QrscanInterroModalArrayList, getApplicationContext());
        coursesRV = findViewById(R.id.idRVQrscanInterro);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(ScanInterroRvAdapter);

        // permition
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        btnexport = findViewById(R.id.btn_xls_export_exam);
        btnexport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String csvFile = courseName+" "+courseDescription+" "+courseDuration+".xls";
                sd = Environment.getExternalStorageDirectory();
                directory = new File(sd.getAbsolutePath());
                file = new File(directory, csvFile);


                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setLocale(new Locale("fr", "FR"));
                Toast.makeText(getApplicationContext(),csvFile+"a ete creer", Toast.LENGTH_SHORT).show();
                try {
                    workbook = createWorkbook(file, wbSettings);
                    createFirstSheet(courseName,courseDescription,courseDuration);
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

            List<QrscanExamenModal> listdata = new ArrayList<>();
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
                QrscanExamenModal modal = listdata.get(i);
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