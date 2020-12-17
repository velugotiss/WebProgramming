package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class dayPlan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Integer quantity = 1;
    EditText PlaceText;
    TextView quantityTextView;
    Button pickCheckInDate;
    Button pickCheckOutDate;
    Calendar c;
    DatePickerDialog dpd;
    TextView checkInDateTextView;
    TextView checkOutDateTextView;
    TextView txt;

    public static String checkInDate;
    public static String checkOutDate;
    public static String adultNum="";
    public static String kidNum="";
    public static String stateOfStay;
    CheckBox pre_checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_plan);
        quantityTextView = findViewById(R.id.quantity);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        pickCheckInDate = findViewById(R.id.pickCheckInDate);
        pickCheckOutDate = findViewById(R.id.pickCheckOutDate);
        checkInDateTextView = findViewById(R.id.textView2);
        checkOutDateTextView = findViewById(R.id.textView4);
        pre_checked = findViewById(R.id.pre_checked);
        txt = findViewById(R.id.text9);
        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        spinner2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        spinner3.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("0");
        categories.add("1");
        categories.add("2");
        categories.add("3");
        categories.add("4");
        categories.add("5");

        // kids Spinner Drop down elements
        List<String> categories3 = new ArrayList<String>();
        categories3.add("0");
        categories3.add("1");
        categories3.add("2");
        categories3.add("3");
        categories3.add("4");
        categories3.add("5");

        // place Spinner Drop down elements
        List<String> categories2 = new ArrayList<String>();
        categories2.add("Kansas");
        categories2.add("Denver");
        categories2.add("New York");
        categories2.add("Boston");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //(------------------------ kids spinner-------------------------)
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories3);

        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner3.setAdapter(dataAdapter3);

        //(------------------------ place spinner-------------------------)
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);

        pickCheckInDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(dayPlan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        checkInDateTextView.setText("Check In :"+   mDay + "/" + (mMonth + 1) + "/" + mYear);
                        checkInDate =  mDay + "/" + (mMonth + 1) + "/" + mYear;
                    }
                },  year,month,day);
                dpd.show();
            }
        });


        pickCheckOutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(dayPlan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        checkOutDateTextView.setText("Check out :"+ mDay + "/" + (mMonth + 1) + "/" + mYear);
                        checkOutDate = mDay + "/" + (mMonth + 1) + "/" + mYear;
                    }
                }, year,month,day);
                dpd.show();
            }
        });

        // set the visibility of text view upon selecting the check box
        pre_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( pre_checked.isChecked() == true) {
                    txt.setVisibility(View.VISIBLE);
                }
                else{
                    txt.setVisibility(View.GONE);
                }
            }
        });

    }

    // validation to check if place is empty
    private boolean isPlaceEmpty(){
        String placeName = PlaceText.getText().toString();
        if(placeName == null || placeName.isEmpty()){
            Context context = getApplicationContext();
           // String upperLimitToast = getString(R.string.place_enter);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, Toast.LENGTH_SHORT, duration);
            toast.show();
            return true;
        }
        return false;
    }

    // increment the quantity
// throw an error if quantity is more than 20
    public void increment(View view) {
        if (quantity < 10) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("DayPlan", "Please select less than 10 rooms");
            Context context = getApplicationContext();
            String lowerLimitToast = "Please select less than 10 rooms";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    // decrement the quantity
// throw an error if quantity is less than one
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        }
        else {
            Log.i("RoomSelect", "Please select at least one room");
            Context context = getApplicationContext();
            String upperLimitToast = "Please select at  least one room";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }

    private void display(int number) {
        quantityTextView.setText("" + number);
    }

    public void save(View view) {
           // placeSummary = PlaceText.toString();
            Intent intent = new Intent(dayPlan.this, SelectionOfServices.class);
            //intent.putExtra("placeSummary", placeSummary);
            startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        if(id <= 3 )
        {
            stateOfStay = item;
        }
        if(id == 4)
        {
            adultNum = item;
        }
        if(id == 5)
        {
            kidNum =item;
        }
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}