package com.example.sliitguru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class gpa_calc extends AppCompatActivity {
    LinearLayout s1,s2,s3,s4,s5,s6,s7,s8;
    TextView cgpaText;
    TextView s1gpa,s1credit;
    TextView s2gpa,s2credit;
    TextView s3gpa,s3credit;
    TextView s4gpa,s4credit;
    TextView s5gpa,s5credit;
    TextView s6gpa,s6credit;
    TextView s7gpa,s7credit;
    TextView s8gpa,s8credit;
    s1DbHelper DB1;
    s2DbHelper DB2;
    s3DbHelper DB3;
    s4DbHelper DB4;
    s5DbHelper DB5;
    s6DbHelper DB6;
    s7DbHelper DB7;
    s8DbHelper DB8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpa_calc);
        s1=findViewById(R.id.s1);
        s2=findViewById(R.id.s2);
        s3=findViewById(R.id.s3);
        s4=findViewById(R.id.s4);
        s5=findViewById(R.id.s5);
        s6=findViewById(R.id.s6);
        s7=findViewById(R.id.s7);
        s8=findViewById(R.id.s8);

        cgpaText=findViewById(R.id.cgpa);

        s1gpa=findViewById(R.id.s1_gpa);
        s1credit=findViewById(R.id.s1_credit);

        s2gpa=findViewById(R.id.s2_gpa);
        s2credit=findViewById(R.id.s2_credit);

        s3gpa=findViewById(R.id.s3_gpa);
        s3credit=findViewById(R.id.s3_credit);

        s4gpa=findViewById(R.id.s4_gpa);
        s4credit=findViewById(R.id.s4_credit);

        s5gpa=findViewById(R.id.s5_gpa);
        s5credit=findViewById(R.id.s5_credit);

        s6gpa=findViewById(R.id.s6_gpa);
        s6credit=findViewById(R.id.s6_credit);

        s7gpa=findViewById(R.id.s7_gpa);
        s7credit=findViewById(R.id.s7_credit);

        s8gpa=findViewById(R.id.s8_gpa);
        s8credit=findViewById(R.id.s8_credit);

        DB1=new s1DbHelper(this);
        Cursor cursor1=DB1.getdata();

        DB2=new s2DbHelper(this);
        Cursor cursor2=DB2.getdata();

        DB3=new s3DbHelper(this);
        Cursor cursor3=DB3.getdata();

        DB4=new s4DbHelper(this);
        Cursor cursor4=DB4.getdata();

        DB5=new s5DbHelper(this);
        Cursor cursor5=DB5.getdata();

        DB6=new s6DbHelper(this);
        Cursor cursor6=DB6.getdata();

        DB7=new s7DbHelper(this);
        Cursor cursor7=DB7.getdata();

        DB8=new s8DbHelper(this);
        Cursor cursor8=DB8.getdata();

        int s1_cr=0,s2_cr=0,s3_cr=0,s4_cr=0,s5_cr=0,s6_cr=0,s7_cr=0,s8_cr=0;
        Double s1_g=0.0,s2_g=0.0,s3_g=0.0,s4_g=0.0,s5_g=0.0,s6_g=0.0,s7_g=0.0,s8_g=0.0;
        Double totalgpa=0.0;
        int totalcredit=0;
        Double cgpa=0.0;



        while (cursor1.moveToNext()){
            String trim_gpa_string = cursor1.getString(21);

            s1gpa.setText("GPA = "+trim_gpa_string.substring(0,3));
            s1credit.setText("Credit = "+cursor1.getString(22));
            s1_cr= Integer.parseInt(cursor1.getString(22));
            s1_g=Double.parseDouble(cursor1.getString(21));
        }

        while (cursor2.moveToNext()){
            String trim_gpa_string = cursor2.getString(21);

            s2gpa.setText("GPA = "+trim_gpa_string.substring(0,3));
            s2credit.setText("Credit = "+cursor2.getString(22));
            s2_cr= Integer.parseInt(cursor2.getString(22));
            s2_g=Double.parseDouble(cursor2.getString(21));
        }

        while (cursor3.moveToNext()){
            String trim_gpa_string = cursor3.getString(21);

            s3gpa.setText("GPA = "+trim_gpa_string.substring(0,3));
            s3credit.setText("Credit = "+cursor3.getString(22));
            s3_cr= Integer.parseInt(cursor3.getString(22));
            s3_g=Double.parseDouble(cursor3.getString(21));
        }

        while (cursor4.moveToNext()){
            String trim_gpa_string = cursor4.getString(21);

            s4gpa.setText("GPA = "+trim_gpa_string.substring(0,3));
            s4credit.setText("Credit = "+cursor4.getString(22));
            s4_cr= Integer.parseInt(cursor4.getString(22));
            s4_g=Double.parseDouble(cursor4.getString(21));
        }

        while (cursor5.moveToNext()){
            String trim_gpa_string = cursor5.getString(21);

            s5gpa.setText("GPA = "+trim_gpa_string.substring(0,3));
            s5credit.setText("Credit = "+cursor5.getString(22));
            s5_cr= Integer.parseInt(cursor5.getString(22));
            s5_g=Double.parseDouble(cursor5.getString(21));
        }
        while (cursor6.moveToNext()){
            String trim_gpa_string = cursor6.getString(21);

            s6gpa.setText("GPA = "+trim_gpa_string.substring(0,3));
            s6credit.setText("Credit = "+cursor6.getString(22));
            s6_cr= Integer.parseInt(cursor6.getString(22));
            s6_g=Double.parseDouble(cursor6.getString(21));
        }

        while (cursor7.moveToNext()){
            String trim_gpa_string = cursor7.getString(21);

            s7gpa.setText("GPA = "+trim_gpa_string.substring(0,3));
            s7credit.setText("Credit = "+cursor7.getString(22));
            s7_cr= Integer.parseInt(cursor7.getString(22));
            s7_g=Double.parseDouble(cursor7.getString(21));
        }

        while (cursor8.moveToNext()){
            String trim_gpa_string = cursor8.getString(21);

            s8gpa.setText("GPA = "+trim_gpa_string.substring(0,3));
            s8credit.setText("Credit = "+cursor8.getString(22));
            s8_cr= Integer.parseInt(cursor8.getString(22));
            s8_g=Double.parseDouble(cursor8.getString(21));
        }

        totalgpa=s1_g+s2_g+s3_g+s4_g+s5_g+s6_g+s7_g+s8_g;
        totalcredit=s1_cr+s2_cr+s3_cr+s4_cr+s5_cr+s6_cr+s7_cr+s8_cr;
        cgpa=totalgpa/totalcredit;

        cgpaText.setText("CGPA: "+String.format("%.2f",cgpa));




        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s1= new Intent(gpa_calc.this,gpa_calculation_s1.class);
                startActivity(s1);
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s2= new Intent(gpa_calc.this,gpa_calculation_s2.class);
                startActivity(s2);
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s3= new Intent(gpa_calc.this,gpa_calculation_s3.class);
                startActivity(s3);
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s4= new Intent(gpa_calc.this,gpa_calculation_s4.class);
                startActivity(s4);
            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s5= new Intent(gpa_calc.this,gpa_calculation_s5.class);
                startActivity(s5);
            }
        });
        s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s6= new Intent(gpa_calc.this,gpa_calculation_s6.class);
                startActivity(s6);
            }
        });
        s7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s7= new Intent(gpa_calc.this,gpa_calculation_s7.class);
                startActivity(s7);
            }
        });
        s8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s8= new Intent(gpa_calc.this,gpa_calculation_s8.class);
                startActivity(s8);
            }
        });
    }
}