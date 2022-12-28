package com.example.sliitguru;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ToDo extends AppCompatActivity {
    FloatingActionButton fap;
    RecyclerView recyclerView;
    todo_DB_Helper DB;
    TodoAdapter adapter;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        fap=findViewById(R.id.add_button);
        recyclerView=findViewById(R.id.recycleview);

        fap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ToDo.this,AddToDoActivity.class);
                startActivity(i);
            }
        });

        DB=new todo_DB_Helper(this);
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void displayData() {
        sqLiteDatabase=DB.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from tododetails",null);
        ArrayList<TodoModel>modelArrayList=new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title=cursor.getString(1);
            String desc=cursor.getString(2);
            modelArrayList.add(new TodoModel(id,title,desc));
        }
        cursor.close();
        adapter= new TodoAdapter(this,R.layout.todosingle,modelArrayList,sqLiteDatabase);
        recyclerView.setAdapter(adapter);
    }

}