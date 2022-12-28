package com.example.sliitguru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddToDoActivity extends AppCompatActivity {

    EditText title,desc;
    Button add,clear,update;
    todo_DB_Helper DB;
    SQLiteDatabase sqLiteDatabase;
    int id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        title=findViewById(R.id.title);
        desc=findViewById(R.id.desc);
        add=findViewById(R.id.add);
        clear=findViewById(R.id.clear);
        update=findViewById(R.id.update);

        DB=new todo_DB_Helper(this);
        insertdata();
        editdata();


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setText("");
                desc.setText("");
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("title",title.getText().toString());
                cv.put("description",desc.getText().toString());

                sqLiteDatabase=DB.getReadableDatabase();
                long recedit=sqLiteDatabase.update("tododetails",cv,"id="+id,null);
                if(recedit!=-1){
                    Toast.makeText(AddToDoActivity.this,"Todo Updated Successfully!",Toast.LENGTH_LONG).show();
                    update.setVisibility(View.GONE);
                    add.setVisibility(View.VISIBLE);
                    //new activity home
                }
                else{
                    Toast.makeText(AddToDoActivity.this,"Todo Update Fail!",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    private void editdata() {

        if(getIntent().getBundleExtra("tododetails")!=null){
            Bundle bundle=getIntent().getBundleExtra("tododetails");
            id=bundle.getInt("id");
            title.setText(bundle.getString("title"));
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
                String desctxt=desc.getText().toString();

                if(titletxt.matches("")||desctxt.matches("")){
                    Toast.makeText(AddToDoActivity.this,"Fill all Fields!",Toast.LENGTH_LONG).show();
                }
                ContentValues cv = new ContentValues();
                cv.put("title",titletxt);
                cv.put("description",desctxt);

                sqLiteDatabase=DB.getWritableDatabase();
                long recinsert=sqLiteDatabase.insert("tododetails",null,cv);
                if(recinsert==-1){
                    Toast.makeText(AddToDoActivity.this,"Todo Added Fail!2",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(AddToDoActivity.this,"Todo Added Successfully!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}