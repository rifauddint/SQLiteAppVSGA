package com.rifauddin.sqlitevsgaproject;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    DataBaseHelper dbHelper;

    EditText editInputNama;
    ListView listViewSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DataBaseHelper(this);
        listViewSiswa = findViewById(R.id.listViewSiswa);
        editInputNama = findViewById(R.id.edInputNama);
    }

    public void onClickSimpan(View view) {
        dbHelper.addStudentDetail(editInputNama.getText().toString());
    }


    public void onClickLoad(View view) {
        ArrayList<String> arrayListSiswa =
                dbHelper.getAllStudentList();

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, arrayListSiswa
                        );

        listViewSiswa.setAdapter(arrayAdapter);


    }


}
