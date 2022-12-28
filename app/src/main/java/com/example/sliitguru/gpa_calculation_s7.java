package com.example.sliitguru;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class gpa_calculation_s7 extends AppCompatActivity  {
    //Define variables
    Spinner m1,m2,m3,m4,m5,m6,m7;
    EditText c1,c2,c3,c4,c5,c6,c7;
    Button calculate,reset;
    TextView gpa,credit;
    TextView n1,n2,n3,n4,n5,n6,n7;

    //Db instance Created
    s7DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa_calculation_s7);

        //Define Db instance
        DB= new s7DbHelper(this);

        //Marks
        m1=findViewById(R.id.m1);
        m2=findViewById(R.id.m2);
        m3=findViewById(R.id.m3);
        m4=findViewById(R.id.m4);
        m5=findViewById(R.id.m5);
        m6=findViewById(R.id.m6);
        m7=findViewById(R.id.m7);

        //Credits
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c4=findViewById(R.id.c4);
        c5=findViewById(R.id.c5);
        c6=findViewById(R.id.c6);
        c7=findViewById(R.id.c7);

        //Name of modules
        n1=findViewById(R.id.n1);
        n2=findViewById(R.id.n2);
        n3=findViewById(R.id.n3);
        n4=findViewById(R.id.n4);
        n5=findViewById(R.id.n5);
        n6=findViewById(R.id.n6);
        n7=findViewById(R.id.n7);

        //Buttons
        calculate=findViewById(R.id.calculate);
        reset=findViewById(R.id.reset);

        //Sem GPA and Credit (Textviews)
        gpa=findViewById(R.id.totalgpa);
        credit=findViewById(R.id.totalcredit);

        //put mark string and their values in array
        String[] marks={"A+","A","A-","B+","B","B-","C+","C","C-","D+","D","D-","E"};
        Double[] markarr={4.0,4.0,3.7,3.3,3.0,2.7,2.3,2.0,1.7,1.3,1.0,0.7,0.0};

        //Function for Assign all 7 dropdown(spinner) with adapter
        setSpinnerValues(marks);

        //get cursor instance from sqlite to get data
        Cursor cursor=DB.getdata();

        //retriving earlier result details from sqlite database
        while (cursor.moveToNext()){
            //set values for each subject names from db
            n1.setText(cursor.getString(0));
            n2.setText(cursor.getString(1));
            n3.setText(cursor.getString(2));
            n4.setText(cursor.getString(3));
            n5.setText(cursor.getString(4));
            n6.setText(cursor.getString(5));
            n7.setText(cursor.getString(6));

            //set values for each subject Grades(Spinner) from db
            for (int i = 0; i < m1.getCount(); i++) {
                if ((m1.getItemAtPosition(i).toString()).equals(cursor.getString(7))) {
                    m1.setSelection(i);
                }
                if (m2.getItemAtPosition(i).equals(cursor.getString(8))) {
                    m2.setSelection(i);
                }
                if (m3.getItemAtPosition(i).equals(cursor.getString(9))) {
                    m3.setSelection(i);

                }
                if (m4.getItemAtPosition(i).equals(cursor.getString(10))) {
                    m4.setSelection(i);

                }
                if (m5.getItemAtPosition(i).equals(cursor.getString(11))) {
                    m5.setSelection(i);

                }
                if (m6.getItemAtPosition(i).equals(cursor.getString(12))) {
                    m6.setSelection(i);

                }
                if (m7.getItemAtPosition(i).equals(cursor.getString(13))) {
                    m7.setSelection(i);

                }
            }

            //set values for each subject credits from db
            c1.setText(cursor.getString(14));
            c2.setText(cursor.getString(15));
            c3.setText(cursor.getString(16));
            c4.setText(cursor.getString(17));
            c5.setText(cursor.getString(18));
            c6.setText(cursor.getString(19));
            c7.setText(cursor.getString(20));

            //set values for total gpa and credit of the sem from db
            String trim_gpa_string = cursor.getString(21);
            if(trim_gpa_string.length()>=4){
                gpa.setText("GPA: "+trim_gpa_string.substring(0,4));
            }
            else{
                gpa.setText("GPA: "+trim_gpa_string.substring(0,3));
            }

            credit.setText("Credit: "+cursor.getString(22));
        }

        // while clicking the calculate button...
        calculate.setOnClickListener(new View.OnClickListener() {

            //predefine the values --> intmark(1-7)= marks for each grade , credit(1-7)=credit for each subject  , total(1-7)= gpa for each subject , Gtotal=Total GPA , Gcredit = Total Credit
            Double intmark1=0.0,intmark2=0.0,intmark3=0.0,intmark4=0.0,intmark5=0.0,intmark6=0.0,intmark7=0.0;
            Double total1=0.0,total2=0.0,total3=0.0,total4=0.0,total5=0.0,total6=0.0,total7=0.0;
            Double Gtotal=0.0;
            int Gcredit=0;
            int credit1=0,credit2=0,credit3=0,credit4=0,credit5=0,credit6=0,credit7=0;
            @Override
            public void onClick(View view) {

                //getting selected grade from each spinner
                String selected_m1=m1.getSelectedItem().toString().trim();
                String selected_m2=m2.getSelectedItem().toString().trim();
                String selected_m3=m3.getSelectedItem().toString().trim();
                String selected_m4=m4.getSelectedItem().toString().trim();
                String selected_m5=m5.getSelectedItem().toString().trim();
                String selected_m6=m6.getSelectedItem().toString().trim();
                String selected_m7=m7.getSelectedItem().toString().trim();

                if(TextUtils.isEmpty(c1.getText().toString())){
                    c1.setText("0");
                }
                if(TextUtils.isEmpty(c2.getText().toString())){
                    c2.setText("0");
                }
                if(TextUtils.isEmpty(c3.getText().toString())){
                    c3.setText("0");
                }
                if(TextUtils.isEmpty(c4.getText().toString())){
                    c4.setText("0");
                }
                if(TextUtils.isEmpty(c5.getText().toString())){
                    c5.setText("0");
                }
                if(TextUtils.isEmpty(c6.getText().toString())){
                    c6.setText("0");
                } if(TextUtils.isEmpty(c7.getText().toString())){
                    c7.setText("0");
                }

                //set subject names to default when its empty
                if(TextUtils.isEmpty(n1.getText().toString())){
                    n1.setText("Module 1");
                }
                if(TextUtils.isEmpty(n2.getText().toString())){
                    n2.setText("Module 2");
                }
                if(TextUtils.isEmpty(n3.getText().toString())){
                    n3.setText("Module 3");
                }
                if(TextUtils.isEmpty(n4.getText().toString())){
                    n4.setText("Module 4");
                }
                if(TextUtils.isEmpty(n5.getText().toString())){
                    n5.setText("Module 5");
                }
                if(TextUtils.isEmpty(n6.getText().toString())){
                    n6.setText("Module 6");
                }
                if(TextUtils.isEmpty(n7.getText().toString())){
                    n7.setText("Module 7");
                }

                /*----------SUBJECT1----------*/

                // check which grade is selected using switch case statement
                switch (selected_m1){

                    //check the case and generate final gpa for each subject
                    case "A+":
                        intmark1=markarr[0];
                        selected_m1="A+";
                        total1=intmark1*(Double.parseDouble(c1.getText().toString()));
                        credit1=Integer.parseInt(c1.getText().toString());
                        break;
                    case "A":

                        intmark1=markarr[1];
                        selected_m1="A";
                        total1=intmark1*(Double.parseDouble(c1.getText().toString()));
                        credit1=Integer.parseInt(c1.getText().toString());
                        break;
                    case "A-":

                        intmark1=markarr[2];
                        selected_m1="A-";
                        total1=intmark1*(Double.parseDouble(c1.getText().toString()));
                        credit1=Integer.parseInt(c1.getText().toString());
                        break;
                    case "B+":

                        intmark1=markarr[3];
                        selected_m1="B+";
                        total1=intmark1*(Double.parseDouble(c1.getText().toString()));
                        credit1=Integer.parseInt(c1.getText().toString());
                        break;
                    case "B":

                        intmark1=markarr[4];
                        selected_m1="B";
                        total1=intmark1*(Double.parseDouble(c1.getText().toString()));
                        credit1=Integer.parseInt(c1.getText().toString());
                        break;
                    case "B-":

                        intmark1=markarr[5];
                        selected_m1="B-";
                        total1=intmark1*(Double.parseDouble(c1.getText().toString()));
                        credit1=Integer.parseInt(c1.getText().toString());
                        break;
                    case "C+":

                        intmark1=markarr[6];
                        selected_m1="C+";
                        total1=intmark1*(Double.parseDouble(c1.getText().toString()));
                        credit1=Integer.parseInt(c1.getText().toString());
                        break;
                    case "C":

                        intmark1=markarr[7];
                        selected_m1="C";
                        total1=intmark1*(Double.parseDouble(c1.getText().toString()));
                        credit1=Integer.parseInt(c1.getText().toString());
                        break;
                    case "C-":

                        intmark1=markarr[8];
                        selected_m1="C-";
                        total1=intmark1*(Double.parseDouble(c1.getText().toString()));
                        credit1=Integer.parseInt(c1.getText().toString());
                        break;
                    case "D+":

                        intmark1=markarr[9];
                        selected_m1="D+";
                        total1=intmark1*(Double.parseDouble(c1.getText().toString()));
                        credit1=Integer.parseInt(c1.getText().toString());
                        break;
                    case "D":

                        intmark1=markarr[10];
                        selected_m1="D";
                        total1=intmark1*(Double.parseDouble(c1.getText().toString()));
                        credit1=Integer.parseInt(c1.getText().toString());
                        break;
                    case "D-":

                        intmark1=markarr[11];
                        selected_m1="D-";
                        total1=intmark1*(Double.parseDouble(c1.getText().toString()));
                        credit1=Integer.parseInt(c1.getText().toString());
                        break;
                    case "E":

                        intmark1=markarr[12];
                        selected_m1="E";
                        total1=intmark1*(Double.parseDouble(c1.getText().toString()));
                        credit1=Integer.parseInt(c1.getText().toString());
                        break;


                }

                /*----------SUBJECT2----------*/

                switch (selected_m2){

                    case "A+":
                        intmark2=markarr[0];
                        selected_m2="A+";
                        total2=intmark2*(Double.parseDouble(c2.getText().toString()));
                        credit2=Integer.parseInt(c2.getText().toString());
                        break;
                    case "A":
                        intmark2=markarr[1];
                        selected_m2="A";
                        total2=intmark2*(Double.parseDouble(c2.getText().toString()));
                        credit2=Integer.parseInt(c2.getText().toString());
                        break;
                    case "A-":
                        intmark2=markarr[2];
                        selected_m2="A-";
                        total2=intmark2*(Double.parseDouble(c2.getText().toString()));
                        credit2=Integer.parseInt(c2.getText().toString());
                        break;
                    case "B+":
                        intmark2=markarr[3];
                        selected_m2="B+";
                        total2=intmark2*(Double.parseDouble(c2.getText().toString()));
                        credit2=Integer.parseInt(c2.getText().toString());
                        break;
                    case "B":
                        intmark2=markarr[4];
                        selected_m2="B";
                        total2=intmark2*(Double.parseDouble(c2.getText().toString()));
                        credit2=Integer.parseInt(c2.getText().toString());
                        break;
                    case "B-":
                        intmark2=markarr[5];
                        selected_m2="B-";
                        total2=intmark2*(Double.parseDouble(c2.getText().toString()));
                        credit2=Integer.parseInt(c2.getText().toString());
                        break;
                    case "C+":
                        intmark2=markarr[6];
                        selected_m2="C+";
                        total2=intmark2*(Double.parseDouble(c2.getText().toString()));
                        credit2=Integer.parseInt(c2.getText().toString());
                        break;
                    case "C":
                        intmark2=markarr[7];
                        selected_m2="C";
                        total2=intmark2*(Double.parseDouble(c2.getText().toString()));
                        credit2=Integer.parseInt(c2.getText().toString());
                        break;
                    case "C-":
                        intmark2=markarr[8];
                        selected_m2="C-";
                        total2=intmark2*(Double.parseDouble(c2.getText().toString()));
                        credit2=Integer.parseInt(c2.getText().toString());
                        break;
                    case "D+":
                        intmark2=markarr[9];
                        selected_m2="D+";
                        total2=intmark2*(Double.parseDouble(c2.getText().toString()));
                        credit2=Integer.parseInt(c2.getText().toString());
                        break;
                    case "D":
                        intmark2=markarr[10];
                        selected_m2="D";
                        total2=intmark2*(Double.parseDouble(c2.getText().toString()));
                        credit2=Integer.parseInt(c2.getText().toString());
                        break;
                    case "D-":
                        intmark2=markarr[11];
                        selected_m2="D-";
                        total2=intmark2*(Double.parseDouble(c2.getText().toString()));
                        credit2=Integer.parseInt(c2.getText().toString());
                        break;
                    case "E":
                        intmark2=markarr[12];
                        selected_m2="E";
                        total2=intmark2*(Double.parseDouble(c2.getText().toString()));
                        credit2=Integer.parseInt(c2.getText().toString());
                        break;

                }


                /*----------SUBJECT3----------*/


                switch (selected_m3){

                    case "A+":
                        intmark3=markarr[0];
                        selected_m3="A+";
                        total3=intmark3*(Double.parseDouble(c3.getText().toString()));
                        credit3=Integer.parseInt(c3.getText().toString());
                        break;
                    case "A":
                        intmark3=markarr[1];
                        selected_m3="A";
                        total3=intmark3*(Double.parseDouble(c3.getText().toString()));
                        credit3=Integer.parseInt(c3.getText().toString());
                        break;
                    case "A-":
                        intmark3=markarr[2];
                        selected_m3="A-";
                        total3=intmark3*(Double.parseDouble(c3.getText().toString()));
                        credit3=Integer.parseInt(c3.getText().toString());
                        break;
                    case "B+":
                        intmark3=markarr[3];
                        selected_m3="B+";
                        total3=intmark3*(Double.parseDouble(c3.getText().toString()));
                        credit3=Integer.parseInt(c3.getText().toString());
                        break;
                    case "B":
                        intmark3=markarr[4];
                        selected_m3="B";
                        total3=intmark3*(Double.parseDouble(c3.getText().toString()));
                        credit3=Integer.parseInt(c3.getText().toString());
                        break;
                    case "B-":
                        intmark3=markarr[5];
                        selected_m3="B-";
                        total3=intmark3*(Double.parseDouble(c3.getText().toString()));
                        credit3=Integer.parseInt(c3.getText().toString());
                        break;
                    case "C+":
                        intmark3=markarr[6];
                        selected_m3="C+";
                        total3=intmark3*(Double.parseDouble(c3.getText().toString()));
                        credit3=Integer.parseInt(c3.getText().toString());
                        break;
                    case "C":
                        intmark3=markarr[7];
                        selected_m3="C";
                        total3=intmark3*(Double.parseDouble(c3.getText().toString()));
                        credit3=Integer.parseInt(c3.getText().toString());
                        break;
                    case "C-":
                        intmark3=markarr[8];
                        selected_m3="C-";
                        total3=intmark3*(Double.parseDouble(c3.getText().toString()));
                        credit3=Integer.parseInt(c3.getText().toString());
                        break;
                    case "D+":
                        intmark3=markarr[9];
                        selected_m3="D+";
                        total3=intmark3*(Double.parseDouble(c3.getText().toString()));
                        credit3=Integer.parseInt(c3.getText().toString());
                        break;
                    case "D":
                        intmark3=markarr[10];
                        selected_m3="D";
                        total3=intmark3*(Double.parseDouble(c3.getText().toString()));
                        credit3=Integer.parseInt(c3.getText().toString());
                        break;
                    case "D-":
                        intmark3=markarr[11];
                        selected_m3="D-";
                        total3=intmark3*(Double.parseDouble(c3.getText().toString()));
                        credit3=Integer.parseInt(c3.getText().toString());
                        break;
                    case "E":
                        intmark3=markarr[12];
                        selected_m3="E";
                        total3=intmark3*(Double.parseDouble(c3.getText().toString()));
                        credit3=Integer.parseInt(c3.getText().toString());
                        break;


                }


                /*----------SUBJECT4----------*/


                switch (selected_m4){

                    case "A+":
                        intmark4=markarr[0];
                        selected_m4="A+";
                        total4=intmark4*(Double.parseDouble(c4.getText().toString()));
                        credit4=Integer.parseInt(c4.getText().toString());
                        break;
                    case "A":
                        intmark4=markarr[1];
                        selected_m4="A";
                        total4=intmark4*(Double.parseDouble(c4.getText().toString()));
                        credit4=Integer.parseInt(c4.getText().toString());
                        break;
                    case "A-":
                        intmark4=markarr[2];
                        selected_m4="A-";
                        total4=intmark4*(Double.parseDouble(c4.getText().toString()));
                        credit4=Integer.parseInt(c4.getText().toString());
                        break;
                    case "B+":
                        intmark4=markarr[3];
                        selected_m4="B+";
                        total4=intmark4*(Double.parseDouble(c4.getText().toString()));
                        credit4=Integer.parseInt(c4.getText().toString());
                        break;
                    case "B":
                        intmark4=markarr[4];
                        selected_m4="B";
                        total4=intmark4*(Double.parseDouble(c4.getText().toString()));
                        credit4=Integer.parseInt(c4.getText().toString());
                        break;
                    case "B-":
                        intmark4=markarr[5];
                        selected_m4="B-";
                        total4=intmark4*(Double.parseDouble(c4.getText().toString()));
                        credit4=Integer.parseInt(c4.getText().toString());
                        break;
                    case "C+":
                        intmark4=markarr[6];
                        selected_m4="C+";
                        total4=intmark4*(Double.parseDouble(c4.getText().toString()));
                        credit4=Integer.parseInt(c4.getText().toString());
                        break;
                    case "C":
                        intmark4=markarr[7];
                        selected_m4="C";
                        total4=intmark4*(Double.parseDouble(c4.getText().toString()));
                        credit4=Integer.parseInt(c4.getText().toString());
                        break;
                    case "C-":
                        intmark4=markarr[8];
                        selected_m4="C-";
                        total4=intmark4*(Double.parseDouble(c4.getText().toString()));
                        credit4=Integer.parseInt(c4.getText().toString());
                        break;
                    case "D+":
                        intmark4=markarr[9];
                        selected_m4="D+";
                        total4=intmark4*(Double.parseDouble(c4.getText().toString()));
                        credit4=Integer.parseInt(c4.getText().toString());
                        break;
                    case "D":
                        intmark4=markarr[10];
                        selected_m4="D";
                        total4=intmark4*(Double.parseDouble(c4.getText().toString()));
                        credit4=Integer.parseInt(c4.getText().toString());
                        break;
                    case "D-":
                        intmark4=markarr[11];
                        selected_m4="D-";
                        total4=intmark4*(Double.parseDouble(c4.getText().toString()));
                        credit4=Integer.parseInt(c4.getText().toString());
                        break;
                    case "E":
                        intmark4=markarr[12];
                        selected_m4="E";
                        total4=intmark4*(Double.parseDouble(c4.getText().toString()));
                        credit4=Integer.parseInt(c4.getText().toString());
                        break;

                }


                /*----------SUBJECT5----------*/


                switch (selected_m5){

                    case "A+":
                        intmark5=markarr[0];
                        selected_m5="A+";
                        total5=intmark5*(Double.parseDouble(c5.getText().toString()));
                        credit5=Integer.parseInt(c5.getText().toString());
                        break;
                    case "A":
                        intmark5=markarr[1];
                        selected_m5="A";
                        total5=intmark5*(Double.parseDouble(c5.getText().toString()));
                        credit5=Integer.parseInt(c5.getText().toString());
                        break;
                    case "A-":
                        intmark5=markarr[2];
                        selected_m5="A-";
                        total5=intmark5*(Double.parseDouble(c5.getText().toString()));
                        credit5=Integer.parseInt(c5.getText().toString());
                        break;
                    case "B+":
                        intmark5=markarr[3];
                        selected_m5="B+";
                        total5=intmark5*(Double.parseDouble(c5.getText().toString()));
                        credit5=Integer.parseInt(c5.getText().toString());
                        break;
                    case "B":
                        intmark5=markarr[4];
                        selected_m5="B";
                        total5=intmark5*(Double.parseDouble(c5.getText().toString()));
                        credit5=Integer.parseInt(c5.getText().toString());
                        break;
                    case "B-":
                        intmark5=markarr[5];
                        selected_m5="B-";
                        total5=intmark5*(Double.parseDouble(c5.getText().toString()));
                        credit5=Integer.parseInt(c5.getText().toString());
                        break;
                    case "C+":
                        intmark5=markarr[6];
                        selected_m5="C+";
                        total5=intmark5*(Double.parseDouble(c5.getText().toString()));
                        credit5=Integer.parseInt(c5.getText().toString());
                        break;
                    case "C":
                        intmark5=markarr[7];
                        selected_m5="C";
                        total5=intmark5*(Double.parseDouble(c5.getText().toString()));
                        credit5=Integer.parseInt(c5.getText().toString());
                        break;
                    case "C-":
                        intmark5=markarr[8];
                        selected_m5="C-";
                        total5=intmark5*(Double.parseDouble(c5.getText().toString()));
                        credit5=Integer.parseInt(c5.getText().toString());
                        break;
                    case "D+":
                        intmark5=markarr[9];
                        selected_m5="D+";
                        total5=intmark5*(Double.parseDouble(c5.getText().toString()));
                        credit5=Integer.parseInt(c5.getText().toString());
                        break;
                    case "D":
                        intmark5=markarr[10];
                        selected_m5="D";
                        total5=intmark5*(Double.parseDouble(c5.getText().toString()));
                        credit5=Integer.parseInt(c5.getText().toString());
                        break;
                    case "D-":
                        intmark5=markarr[11];
                        selected_m5="D-";
                        total5=intmark5*(Double.parseDouble(c5.getText().toString()));
                        credit5=Integer.parseInt(c5.getText().toString());
                        break;
                    case "E":
                        intmark5=markarr[12];
                        selected_m5="E";
                        total5=intmark5*(Double.parseDouble(c5.getText().toString()));
                        credit5=Integer.parseInt(c5.getText().toString());
                        break;


                }



                /*----------SUBJECT6----------*/

                switch (selected_m6){

                    case "A+":
                        intmark6=markarr[0];
                        selected_m6="A+";
                        total6=intmark6*(Double.parseDouble(c6.getText().toString()));
                        credit6=Integer.parseInt(c6.getText().toString());
                        break;
                    case "A":
                        intmark6=markarr[1];
                        selected_m6="A";
                        total6=intmark6*(Double.parseDouble(c6.getText().toString()));
                        credit6=Integer.parseInt(c6.getText().toString());
                        break;
                    case "A-":
                        intmark6=markarr[2];
                        selected_m6="A-";
                        total6=intmark6*(Double.parseDouble(c6.getText().toString()));
                        credit6=Integer.parseInt(c6.getText().toString());
                        break;
                    case "B+":
                        intmark6=markarr[3];
                        selected_m6="B+";
                        total6=intmark6*(Double.parseDouble(c6.getText().toString()));
                        credit6=Integer.parseInt(c6.getText().toString());
                        break;
                    case "B":
                        intmark6=markarr[4];
                        selected_m6="B";
                        total6=intmark6*(Double.parseDouble(c6.getText().toString()));
                        credit6=Integer.parseInt(c6.getText().toString());
                        break;
                    case "B-":
                        intmark6=markarr[5];
                        selected_m6="B-";
                        total6=intmark6*(Double.parseDouble(c6.getText().toString()));
                        credit6=Integer.parseInt(c6.getText().toString());
                        break;
                    case "C+":
                        intmark6=markarr[6];
                        selected_m6="E";
                        total6=intmark6*(Double.parseDouble(c6.getText().toString()));
                        credit6=Integer.parseInt(c6.getText().toString());
                        break;
                    case "C":
                        intmark6=markarr[7];
                        selected_m6="C";
                        total6=intmark6*(Double.parseDouble(c6.getText().toString()));
                        credit6=Integer.parseInt(c6.getText().toString());
                        break;
                    case "C-":
                        intmark6=markarr[8];
                        selected_m6="C-";
                        total6=intmark6*(Double.parseDouble(c6.getText().toString()));
                        credit6=Integer.parseInt(c6.getText().toString());
                        break;
                    case "D+":
                        intmark6=markarr[9];
                        selected_m6="D+";
                        total6=intmark6*(Double.parseDouble(c6.getText().toString()));
                        credit6=Integer.parseInt(c6.getText().toString());
                        break;
                    case "D":
                        intmark6=markarr[10];
                        selected_m6="D";
                        total6=intmark6*(Double.parseDouble(c6.getText().toString()));
                        credit6=Integer.parseInt(c6.getText().toString());
                        break;
                    case "D-":
                        intmark6=markarr[11];
                        selected_m6="D-";
                        total6=intmark6*(Double.parseDouble(c6.getText().toString()));
                        credit6=Integer.parseInt(c6.getText().toString());
                        break;
                    case "E":
                        intmark6=markarr[12];
                        selected_m6="E";
                        total6=intmark6*(Double.parseDouble(c6.getText().toString()));
                        credit6=Integer.parseInt(c6.getText().toString());
                        break;



                }



                /*----------SUBJECT7----------*/

                switch (selected_m7){

                    case "A+":
                        intmark7=markarr[0];
                        selected_m7="A+";
                        total7=intmark7*(Double.parseDouble(c7.getText().toString()));
                        credit7=Integer.parseInt(c7.getText().toString());
                        break;
                    case "A":
                        intmark7=markarr[1];
                        selected_m7="A";
                        total7=intmark7*(Double.parseDouble(c7.getText().toString()));
                        credit7=Integer.parseInt(c7.getText().toString());
                        break;
                    case "A-":
                        intmark7=markarr[2];
                        selected_m7="A-";
                        total7=intmark7*(Double.parseDouble(c7.getText().toString()));
                        credit7=Integer.parseInt(c7.getText().toString());
                        break;
                    case "B+":
                        intmark7=markarr[3];
                        selected_m7="B+";
                        total7=intmark7*(Double.parseDouble(c7.getText().toString()));
                        credit7=Integer.parseInt(c7.getText().toString());
                        break;
                    case "B":
                        intmark7=markarr[4];
                        selected_m7="B";
                        total7=intmark7*(Double.parseDouble(c7.getText().toString()));
                        credit7=Integer.parseInt(c7.getText().toString());
                        break;
                    case "B-":
                        intmark7=markarr[5];
                        selected_m7="B-";
                        total7=intmark7*(Double.parseDouble(c7.getText().toString()));
                        credit7=Integer.parseInt(c7.getText().toString());
                        break;
                    case "C+":
                        intmark7=markarr[6];
                        selected_m7="C+";
                        total7=intmark7*(Double.parseDouble(c7.getText().toString()));
                        credit7=Integer.parseInt(c7.getText().toString());
                        break;
                    case "C":
                        intmark7=markarr[7];
                        selected_m7="C";
                        total7=intmark7*(Double.parseDouble(c7.getText().toString()));
                        credit7=Integer.parseInt(c7.getText().toString());
                        break;
                    case "C-":
                        intmark7=markarr[8];
                        selected_m7="C-";
                        total7=intmark7*(Double.parseDouble(c7.getText().toString()));
                        credit7=Integer.parseInt(c7.getText().toString());
                        break;
                    case "D+":
                        intmark7=markarr[9];
                        selected_m7="D+";
                        total7=intmark7*(Double.parseDouble(c7.getText().toString()));
                        credit7=Integer.parseInt(c7.getText().toString());
                        break;
                    case "D":
                        intmark7=markarr[10];
                        selected_m7="D";
                        total7=intmark7*(Double.parseDouble(c7.getText().toString()));
                        credit7=Integer.parseInt(c7.getText().toString());
                        break;
                    case "D-":
                        intmark7=markarr[11];
                        selected_m7="D-";
                        total7=intmark7*(Double.parseDouble(c7.getText().toString()));
                        credit7=Integer.parseInt(c7.getText().toString());
                        break;
                    case "E":
                        intmark7=markarr[12];
                        selected_m7="E";
                        total7=intmark7*(Double.parseDouble(c7.getText().toString()));
                        credit7=Integer.parseInt(c7.getText().toString());
                        break;


                }


                //get the final text from each subject names to insert into Db
                String name1=n1.getText().toString();
                String name2=n2.getText().toString();
                String name3=n3.getText().toString();
                String name4=n4.getText().toString();
                String name5=n5.getText().toString();
                String name6=n6.getText().toString();
                String name7=n7.getText().toString();

                //get the final text from each subject credit to insert into Db
                String cr1=c1.getText().toString();
                String cr2=c2.getText().toString();
                String cr3=c3.getText().toString();
                String cr4=c4.getText().toString();
                String cr5=c5.getText().toString();
                String cr6=c6.getText().toString();
                String cr7=c7.getText().toString();


                //calculating final GPA and Credits for this semester
                Gtotal=(total1+total2+total3+total4+total5+total6+total7);
                Gcredit=credit1+credit2+credit3+credit4+credit5+credit6+credit7;
                Double finalgpa=Gtotal/Gcredit;
                gpa.setText("GPA : "+String.format("%.2f",finalgpa));
                credit.setText("Credit : "+Gcredit);

                //final gpa and credits to insert into Db
                String Tgpa=finalgpa.toString().trim();
                String Tcr=String.valueOf(Gcredit);


                //Update the Data of Db
                Boolean updateinsertdata=DB.updatedata(name1,name2,name3,name4,name5,name6,name7,selected_m1,selected_m2,selected_m3,selected_m4,selected_m5,selected_m6,selected_m7,cr1,cr2,cr3,cr4,cr5,cr6,cr7,Tgpa,Tcr);
                if(updateinsertdata==true){
                    Toast.makeText(gpa_calculation_s7.this,"New Data Updated!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(gpa_calculation_s7.this,"New Data Update Fail!",Toast.LENGTH_SHORT).show();
                }



                //Insert the Data into Db
                Boolean checkinsertdata=DB.insertdata(name1,name2,name3,name4,name5,name6,name7,selected_m1,selected_m2,selected_m3,selected_m4,selected_m5,selected_m6,selected_m7,cr1,cr2,cr3,cr4,cr5,cr6,cr7,Tgpa,Tcr);
                if(checkinsertdata==true){
                    Toast.makeText(gpa_calculation_s7.this,"New Data Inserted!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(gpa_calculation_s7.this,"New Data Insertion Fail!",Toast.LENGTH_SHORT).show();
                }



                //log data for testing purpose
               /* Log.d("total1=====",total1.toString().trim());
                Log.d("total2====",total2.toString().trim());
                Log.d("total3====",total3.toString().trim());
                Log.d("total4====",total4.toString().trim());
                Log.d("total5====",total5.toString().trim());
                Log.d("total6====",total6.toString().trim());
                Log.d("total7====",total7.toString().trim());

                Log.d("mark1=====",intmark1.toString().trim());
                Log.d("mark2====",intmark2.toString().trim());
                Log.d("mark3====",intmark3.toString().trim());
                Log.d("mark4====",intmark4.toString().trim());
                Log.d("mark5====",intmark5.toString().trim());
                Log.d("mark6====",intmark6.toString().trim());
                Log.d("mark7====",intmark7.toString().trim());

                Log.d("selectedmark1=====",selected_m1);
                Log.d("selectedmark2====",selected_m2);
                Log.d("selectedmark3====",selected_m3);
                Log.d("selectedmark4====",selected_m4);
                Log.d("selectedmark5====",selected_m5);
                Log.d("selectedmark6====",selected_m6);
                Log.d("selectedmark7====",selected_m7);

                Log.d("cursor 7",cursor.getString(7));
                Log.d("cursor 8",cursor.getString(8));
                Log.d("cursor 9",cursor.getString(9));
                Log.d("cursor 10",cursor.getString(10));
                Log.d("cursor 11",cursor.getString(11));
                Log.d("cursor 12",cursor.getString(12));
                Log.d("cursor 13",cursor.getString(13));*/



            }
        });

        //while clicking reset button
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1.setText("Module1");
                n2.setText("Module2");
                n3.setText("Module3");
                n4.setText("Module4");
                n5.setText("Module5");
                n6.setText("Module6");
                n7.setText("Module7");

                m1.setSelection(12);
                m2.setSelection(12);
                m3.setSelection(12);
                m4.setSelection(12);
                m5.setSelection(12);
                m6.setSelection(12);
                m7.setSelection(12);

                c1.setText("0");
                c2.setText("0");
                c3.setText("0");
                c4.setText("0");
                c5.setText("0");
                c6.setText("0");
                c7.setText("0");

                gpa.setText("GPA : 0.00");
                credit.setText("Credit : 0");


            }
        });

        DB.close();
    }

    //Assign all 7 dropdown(spinner) with adapter
    private void setSpinnerValues(String[] marks) {

        ArrayAdapter<String> adapter_m1= new ArrayAdapter<String>(gpa_calculation_s7.this, android.R.layout.simple_spinner_dropdown_item,marks);
        adapter_m1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m1.setAdapter(adapter_m1);


        ArrayAdapter<String> adapter_m2= new ArrayAdapter<String>(gpa_calculation_s7.this, android.R.layout.simple_spinner_dropdown_item,marks);
        adapter_m1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m2.setAdapter(adapter_m2);


        ArrayAdapter<String> adapter_m3= new ArrayAdapter<String>(gpa_calculation_s7.this, android.R.layout.simple_spinner_dropdown_item,marks);
        adapter_m1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m3.setAdapter(adapter_m3);


        ArrayAdapter<String> adapter_m4= new ArrayAdapter<String>(gpa_calculation_s7.this, android.R.layout.simple_spinner_dropdown_item,marks);
        adapter_m1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m4.setAdapter(adapter_m4);


        ArrayAdapter<String> adapter_m5= new ArrayAdapter<String>(gpa_calculation_s7.this, android.R.layout.simple_spinner_dropdown_item,marks);
        adapter_m1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m5.setAdapter(adapter_m5);


        ArrayAdapter<String> adapter_m6= new ArrayAdapter<String>(gpa_calculation_s7.this, android.R.layout.simple_spinner_dropdown_item,marks);
        adapter_m1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m6.setAdapter(adapter_m6);


        ArrayAdapter<String> adapter_m7= new ArrayAdapter<String>(gpa_calculation_s7.this, android.R.layout.simple_spinner_dropdown_item,marks);
        adapter_m1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m7.setAdapter(adapter_m7);
    }


}