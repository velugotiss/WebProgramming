package com.csee5590.helloworldapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import com.csee5590.helloworldapp.R;

public class MainActivity extends AppCompatActivity {
    Button Loginbutton;
    EditText ControlUsername;
    EditText ControlPassword;
    TextView ControlStatus;
    String uname;
    String pwd;
    boolean flag = false;
    //Function to acquire login credentials and comparing with the given credentials
    //if matched then it redirects to welcome page else it displays incorrect password
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ControlUsername = findViewById(R.id.editTextTextPersonName);
        ControlPassword = findViewById(R.id.editTextTextPassword);
        ControlStatus = findViewById(R.id.textView);
        uname = ControlUsername.getText().toString();
        pwd = ControlPassword.getText().toString();
        Loginbutton = findViewById(R.id.login123456);
        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ControlUsername.getText().toString().isEmpty() && !ControlPassword.getText().toString().isEmpty()) {
                    if (ControlUsername.getText().toString().equals("sv6by") && ControlPassword.getText().toString().equals("srinivas"))
                    { flag = true; }
                }
                if (!flag)
                { ControlStatus.setText("Incorrect Username or Password"); }
                else
//                {
//                    ControlStatus.setText("Success");
//                }
                { reDirectToWelcomePage(); }
            }
        });
    }
    public void reDirectToWelcomePage () {
        Intent intent = new Intent(MainActivity.this, MainPage.class);
        startActivity(intent);
    }
}
