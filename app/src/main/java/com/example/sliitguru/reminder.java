package com.example.sliitguru;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

//this is for adding new reminder not for showing or recycle view
public class reminder extends AppCompatActivity {
    Button timeButton;
    int hour, minute;
    int year,month,day;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button add;
    private Button clear;
    private EditText title;
    private EditText detail;


    private reminderDbHelper helper;
    private SQLiteDatabase sqLiteDatabase;
    private int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        helper = new reminderDbHelper(getApplicationContext());

        //Component Definition
        timeButton = findViewById(R.id.timeButton);
        dateButton = findViewById(R.id.datePickerButton);
        title=findViewById(R.id.title);
        detail=findViewById(R.id.detail);
        add=findViewById(R.id.add);
        clear=findViewById(R.id.clear);


        initDatePicker();

        dateButton.setText(getTodaysDate());

        editData();

        //save data to SQLite database
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(detail.getText().toString().matches("")||title.getText().toString().matches("")||timeButton.getText().toString().matches("Select Time")){
                    Toast.makeText(reminder.this,"All the fields are mandatory to fill",Toast.LENGTH_SHORT).show();
                }
                else{
                    ContentValues contentValues=new ContentValues();
                    contentValues.put("title",title.getText().toString());
                    contentValues.put("detail",detail.getText().toString());
                    contentValues.put("date",dateButton.getText().toString());
                    contentValues.put("time",timeButton.getText().toString());

                    sqLiteDatabase=helper.getWritableDatabase();
                    Long recinsert=sqLiteDatabase.insert("reminder",null,contentValues);

                    NotificationChannel();
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    calendar.set(Calendar.SECOND, 00);
                    if (Calendar.getInstance().after(calendar)) {
//
                        calendar.add(Calendar.DAY_OF_MONTH , day) ;
                        calendar.add(Calendar.MONTH,month);
                        calendar.add(Calendar.YEAR,year);
                    }

                    Intent intent = new Intent(reminder.this, MemoBroadcast.class);
                    Bundle b = new Bundle();
                    b.putString("Title","SLIIT GURU Reminder");
                    b.putString("Desc","Your Have Reminder");
                    intent.putExtra("Details",b);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                    }




                    if(recinsert!=null){


                        Toast.makeText(reminder.this,"New Reminder Added",Toast.LENGTH_LONG).show();

                        //Log.i("minute",minute);
                        //Log.i("hour",hour);

                        title.setText("");
                        detail.setText("");
                        dateButton.setText("Select Date");
                        timeButton.setText("Select Time");
                        //Log.e(TAG,"insertdata:"+recinsert);
                    }else{
                        Toast.makeText(reminder.this,"Something went wrong",Toast.LENGTH_LONG).show();
                    }


                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setText("");
                detail.setText("");
                dateButton.setText("Select Date");
                timeButton.setText("Select Time");
            }
        });
    }

    private void NotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "REMINDY Clinic & Report";
            String description = "REMINDY`S CHANNEL";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification Clinic", name, importance);
            channel.setDescription(description);


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }

    private void editData() {
        if(getIntent().getBundleExtra("reminderdata")!=null){
            Bundle bundle=getIntent().getBundleExtra("reminderdata");
            id=bundle.getInt("id");
            title.setText(bundle.getString("title"));
            detail.setText(bundle.getString("detail"));
            dateButton.setText(bundle.getString("date"));
            timeButton.setText(bundle.getString("time"));
        }
    }


    public void popTimePicker (View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));

            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
    //change to public
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + ", " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "Jan";
        if(month == 2)
            return "Feb";
        if(month == 3)
            return "Mar";
        if(month == 4)
            return "Apr";
        if(month == 5)
            return "May";
        if(month == 6)
            return "Jun";
        if(month == 7)
            return "Jul";
        if(month == 8)
            return "Aug";
        if(month == 9)
            return "Sep";
        if(month == 10)
            return "Oct";
        if(month == 11)
            return "Nov";
        if(month == 12)
            return "Dec";

        //default should never happen
        return "Jan";
    }




    public void openDatePicker (View view){
        datePickerDialog.show();
    }

}