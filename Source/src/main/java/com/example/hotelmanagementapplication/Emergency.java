package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Emergency extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;
    TextView txt;
    CheckBox pre_checked;
    CheckBox theft;
    CheckBox fire;
    CheckBox child;
    CheckBox button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        radioGroup = findViewById(R.id.raioGroup);
        txt = findViewById(R.id.txt);
        pre_checked = findViewById(R.id.medical);
        theft = findViewById(R.id.theft);
        fire = findViewById(R.id.fire);
        child = findViewById(R.id.child);
        button5 = findViewById(R.id.fireexit);

        // set the visibility of text view upon selecting the Medical Emergency check box
        pre_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m1();
            }
        });

        // set the visibility of text view upon selecting the Theft check box
        theft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m1();
            }
        });

        // set the visibility of text view upon selecting the Fire check box
        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m1();
            }
        });

        // set the visibility of text view upon selecting the Lost Child check box
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m1();
            }
        });

    }

    // set the visibility of text view upon selecting any one of the check box
    public void m1(){
        if( pre_checked.isChecked() == true) {
            txt.setVisibility(View.VISIBLE);
        }
        else if( theft.isChecked() == true) {
            txt.setVisibility(View.VISIBLE);
        }
        else if( fire.isChecked() == true) {
            txt.setVisibility(View.VISIBLE);
        }
        else if( child.isChecked() == true) {
            txt.setVisibility(View.VISIBLE);
        }
        else{
            txt.setVisibility(View.GONE);
        }
    }

    // navigate to next screen when user clicks on sumit button
    public void submit(View view) {
        Intent intent = new Intent(Emergency.this, seivicePref.class); //->change SelectionOfServices.class
        startActivity(intent);
    }

    public void fireExit(View view) {

        Intent intent = new Intent(Emergency.this, FireExit.class); //->change SelectionOfServices.class
        startActivity(intent);

    }
}