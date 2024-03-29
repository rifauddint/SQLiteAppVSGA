package com.rifauddin.sqlitevsgaproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "student_database";
    private static final int DATABASE_VERSION = 1;
    private  static final String TABLE_STUDENTS = "student";
    private  static final String KEY_ID = "id";
    private  static final String KEY_FIRSTNAME = "name";

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        addStudentDetail("GOKU");
        addStudentDetail("GOHAN");
        addStudentDetail("GOTEN");

        ArrayList<String> dataSiswa = getAllStudentList();


    }



    @Override
    public void onCreate(SQLiteDatabase db) {

         String CREATE_TABLE_STUDENTS =
                String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",
                        TABLE_STUDENTS,
                        KEY_ID,
                        KEY_FIRSTNAME);

//        "CREATE TABLE " + TABLE_STUDENTS +
//                "("+KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
//                KEY_FIRSTNAME+ " TEXT)";

         db.execSQL(CREATE_TABLE_STUDENTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS'"+TABLE_STUDENTS+"'");
        onCreate(db);

    }

    public long addStudentDetail(String student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, student);
        long insert = db.insert(TABLE_STUDENTS, null, values);
        Log.i("DBASE", insert+"");
        return  insert;

    }

    public ArrayList<String> getAllStudentList(){
        ArrayList<String> studentArrayList =
                new ArrayList<String>();

        String name = "";
        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        c.moveToPosition(-1);

        while (c.moveToNext()){
            name = c.getString(c.getColumnIndex(KEY_FIRSTNAME));
            studentArrayList.add(name);
        }

        Log.i("DBASE", studentArrayList.toString());

        return studentArrayList;



    }



}
