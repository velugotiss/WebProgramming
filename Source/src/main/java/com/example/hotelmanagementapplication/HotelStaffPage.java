package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HotelStaffPage extends AppCompatActivity {

    private TextView loggedInName;
    private TextView dateTime;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_staff_page);

        loggedInName = findViewById(R.id.loggedInGuestNameHotelStaff);
        dateTime = findViewById(R.id.DateTime);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        dateTime.setText( timeStamp);
        b = findViewById(R.id.checkMsg);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = "Hotel staff page ";
            loggedInName.setText(value);
        }
    }
    public void serviceRequestHandle(View view) {
        //Alert dialog box to check if customer wants to move on to payment or has to make changes in pizza

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Messages");
        alert.setMessage(serviceRequest.serviceRequest);

        //Positive response. Making changes with pizza before moving forward
        alert.setPositiveButton("Assign", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(HotelStaffPage.this, "Assigning to a housekeeper", Toast.LENGTH_LONG).show();
            }
        });
        //Moving forward to payment.
        alert.setNegativeButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(HotelStaffPage.this, "Request completed", Toast.LENGTH_LONG).show();
                Intent i = new Intent(HotelStaffPage.this, MainActivity.class);
                startActivity(i);
            }
        });
        alert.create().show();
    }
}