package com.example.sliitguru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class linkeradd extends AppCompatActivity {

    EditText title,link,desc;
    Button add,clear,update;
    linkerDbHelper DB;
    SQLiteDatabase sqLiteDatabase;
    int id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkeradd);


        title=findViewById(R.id.title);
        link=findViewById(R.id.link);
        desc=findViewById(R.id.desc);
        add=findViewById(R.id.add);
        clear=findViewById(R.id.clear);
        update=findViewById(R.id.update);

        DB=new linkerDbHelper(this);
        insertdata();
        editdata();



        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setText("");
                link.setText("");
                desc.setText("");
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("title",title.getText().toString());
                cv.put("link",link.getText().toString());
                cv.put("description",desc.getText().toString());

                sqLiteDatabase=DB.getReadableDatabase();
                long recedit=sqLiteDatabase.update("linkerdetails",cv,"id="+id,null);
                if(recedit!=-1){
                    Toast.makeText(linkeradd.this,"Link Updated Successfully!",Toast.LENGTH_LONG).show();
                    update.setVisibility(View.GONE);
                    add.setVisibility(View.VISIBLE);
                    //new activity home
                }
                else{
                    Toast.makeText(linkeradd.this,"Link Update Fail!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void editdata() {

        if(getIntent().getBundleExtra("linkdetails")!=null){
            Bundle bundle=getIntent().getBundleExtra("linkdetails");
            id=bundle.getInt("id");
            title.setText(bundle.getString("title"));
            link.setText(bundle.getString("link"));
            desc.setText(bundle.getString("description"));
            add.setVisibility(View.GONE);
            update.setVisibility(View.VISIBLE);

        }
    }

    private void insertdata() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titletxt=title.getText().toString();
                String linktxt=link.getText().toString();
                String desctxt=desc.getText().toString();

                if(titletxt.matches("")||linktxt.matches("")||desctxt.matches("")){
                    Toast.makeText(linkeradd.this,"Fill all Fields!",Toast.LENGTH_LONG).show();
                }
                ContentValues cv = new ContentValues();
                cv.put("title",titletxt);
                cv.put("description",desctxt);
                cv.put("link",linktxt);

                sqLiteDatabase=DB.getWritableDatabase();
                long recinsert=sqLiteDatabase.insert("linkerdetails",null,cv);
                if(recinsert==-1){
                    Toast.makeText(linkeradd.this,"Link Added Fail!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(linkeradd.this,"Link Added Successfully!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}