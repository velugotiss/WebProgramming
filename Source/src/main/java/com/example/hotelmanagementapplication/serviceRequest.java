package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class serviceRequest extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView txt;
    CheckBox pre_checked;
    Button button2;
    public static String serviceRequest="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_request);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        txt = findViewById(R.id.text9sr);
        pre_checked = findViewById(R.id.pre_checked);
        button2 = findViewById(R.id.button2sr);

        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        spinner2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Low");
        categories.add("Medium");
        categories.add("High");

        // place Spinner Drop down elements
        List<String> categories2 = new ArrayList<String>();
        categories2.add("House Keeping");
        categories2.add("Pantry");
        categories2.add("Reception");
        categories2.add("Valet");
        categories2.add("Laundry");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //(------------------------ request/complaint spinner-------------------------)
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);

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

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                serviceRequest = txt.getText().toString();
                Intent intent = new Intent(serviceRequest.this, WelcomeGuest.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}