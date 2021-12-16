package com.example.utambulishoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.utambulishoversion.modal.QrscanInterroModal;

import java.util.ArrayList;

public class DBscanInterro extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "Interroscandb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "qrscan";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "scan";

    // below variable id for our course duration column.
    private static final String DURATION_COL = "cours";

    // below variable for our course description column.
    private static final String DESCRIPTION_COL = "promotion";

    // below variable is for our course tracks column.
    private static final String TRACKS_COL = "nom";

    // creating a constructor for our database handler.
    public DBscanInterro(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DURATION_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + TRACKS_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewElement(String coursescan, String courseduration, String coursepromotion, String courseName) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, coursescan);
        values.put(DURATION_COL, courseduration);
        values.put(DESCRIPTION_COL, coursepromotion);
        values.put(TRACKS_COL, courseName);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the courses.
    public ArrayList<QrscanInterroModal> readElement() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<QrscanInterroModal> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new QrscanInterroModal(cursorCourses.getString(1),
                        ((Cursor) cursorCourses).getString(2),
                        cursorCourses.getString(4),
                        cursorCourses.getString(3)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }
    // we have created a new method for reading all the courses.
    public ArrayList<QrscanInterroModal> readElementInterro(String courseName,String courseDescription,String courseTrack) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE nom=='"+courseName+"' AND promotion=='"+courseDescription+"'AND cours=='"+courseTrack+"'", null);

        // on below line we are creating a new array list.
        ArrayList<QrscanInterroModal> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new QrscanInterroModal(cursorCourses.getString(1),
                        ((Cursor) cursorCourses).getString(2),
                        cursorCourses.getString(4),
                        cursorCourses.getString(3)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }
    // we have created a new method for reading all the courses.
    public ArrayList<QrscanInterroModal> readElementQRinterro(String courseDescription,String courseTracks) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE promotion=='"+courseDescription+"' AND session=='"+courseTracks+"'", null);

        // on below line we are creating a new array list.
        ArrayList<QrscanInterroModal> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new QrscanInterroModal(cursorCourses.getString(1),
                        ((Cursor) cursorCourses).getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

