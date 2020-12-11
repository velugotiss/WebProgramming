package com.example.calenderevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText title;
    EditText location;
    EditText description;
    EditText allDay;
    Button addEvent;
    CalendarView calendarView;
    Date date1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        location = findViewById(R.id.location);
        description = findViewById(R.id.description);
        addEvent = findViewById( R.id.button);
        allDay = findViewById(R.id.allDay);
        calendarView = findViewById(R.id.calendarView);
        textView = findViewById(R.id.textView);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                Date date2 = new GregorianCalendar(year, month - 1, dayOfMonth).getTime();
                date1 = date2;
                textView.setText("Event being set for date : " + date);

            }
        });



      addEvent.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty() && !description.getText().toString().isEmpty())
              {

                  Intent intent = new Intent(Intent.ACTION_INSERT);
                  intent.setData(CalendarContract.Events.CONTENT_URI);
                  //Find a way to fill different date
                  intent.putExtra(CalendarContract.Events.DTSTART,date1);
                  intent.putExtra(CalendarContract.Events.TITLE,title.getText().toString());
                  intent.putExtra(CalendarContract.Events.EVENT_LOCATION,location.getText().toString());
                  intent.putExtra(CalendarContract.Events.DESCRIPTION,description.getText().toString());
                  intent.putExtra(CalendarContract.Events.ALL_DAY,allDay.getText().toString());
                  intent.putExtra(Intent.EXTRA_EMAIL,"test@gmail.com");

                  if(intent.resolveActivity(getPackageManager()) != null)
                  {
                      startActivity(intent);
                  }else
                  {
                      Toast.makeText(MainActivity.this,"No app to support this" , Toast.LENGTH_SHORT).show();
                  }

              }
              else
              {
                  Toast.makeText(MainActivity.this,"PLease fill all the fileds" , Toast.LENGTH_SHORT).show();
              }
          }
      });




    }
}