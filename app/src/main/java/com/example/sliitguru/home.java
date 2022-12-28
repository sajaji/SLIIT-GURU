package com.example.sliitguru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class home extends AppCompatActivity {
    CardView gpa,todo,reminder,linker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gpa=findViewById(R.id.gpa);
        todo=findViewById(R.id.todo);
        reminder=findViewById(R.id.reminder);
        linker=findViewById(R.id.social);

        gpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,gpa_calc.class);
                startActivity(i);
            }
        });

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(home.this,ToDo.class);
                startActivity(j);
            }
        });

        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(home.this,addreminder.class);
                startActivity(k);
            }
        });

        linker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l = new Intent(home.this,linker.class);
                startActivity(l);
            }
        });
    }
}