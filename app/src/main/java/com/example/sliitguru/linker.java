package com.example.sliitguru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class linker extends AppCompatActivity {

    FloatingActionButton fap;
    RecyclerView recyclerView;
    linkerDbHelper DB;
    linkerAdapter adapter;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linker);
        fap=findViewById(R.id.floatingActionButton);
        recyclerView=findViewById(R.id.recycleview);

        fap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(linker.this,linkeradd.class);
                startActivity(i);
            }
        });

        DB=new linkerDbHelper(this);
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void displayData() {
        sqLiteDatabase=DB.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from linkerdetails",null);
        ArrayList<linkerModel>modelArrayList=new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title=cursor.getString(1);
            String link=cursor.getString(2);
            String desc=cursor.getString(3);
            modelArrayList.add(new linkerModel(id,title,link,desc));
        }
        cursor.close();
        adapter= new linkerAdapter(this,R.layout.linkersingle,modelArrayList,sqLiteDatabase);
        recyclerView.setAdapter(adapter);
    }


}