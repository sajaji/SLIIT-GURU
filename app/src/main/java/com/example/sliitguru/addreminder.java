package com.example.sliitguru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class addreminder extends AppCompatActivity {
    reminderDbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    reminderAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreminder);

        recyclerView=findViewById(R.id.rv);
        fab=findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(addreminder.this,reminder.class);
                startActivity(i);
            }
        });

        dbHelper=new reminderDbHelper(this);
        //create method
        displayData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void displayData() {
        sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from reminder ",null);
        ArrayList<reminderDataModel> modelArrayList=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String title=cursor.getString(1);
            String detail=cursor.getString(2);
            String date=cursor.getString(3);
            String time=cursor.getString(4);
            modelArrayList.add(new reminderDataModel(id,title,detail,date,time));
        }
        cursor.close();
        adapter=new reminderAdapter(this,R.layout.reminderdatasingle,modelArrayList,sqLiteDatabase);
        recyclerView.setAdapter(adapter);
    }



}