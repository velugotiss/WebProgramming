package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FireExit extends AppCompatActivity {

    int j=0;
    ImageView im;
    TextView instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_exit);
        instruction = findViewById(R.id.instruction);
    }

    // Dynamically return the text(instructions)
    public String getTxt(int i)
    {
        switch (i)
        {
            case 0 :  { return "In case of fire, elevators are out of service. Use Exit stairs."; }
            case 1 :  { return "If a Fire Starts within your room:" + "\n" + "\n" +
                    "1. Leave the room immediately, closing the door behind you." + "\n" + "\n" +
                    "2. Pull the nearest fire alarm pull station then call 911.";   }
            case 2 :  { return "Feel Door to see if warm.\n" +  "\n" +
                    "1. If warm, remain in room and call for Help\n" +  "\n" +
                    "2. Wake suitemates\n" +  "\n" +
                    "3. Close windows and doors as you leave\n" + "\n" +
                    "4. Pull alarm"; }
            case 3 :  { return "1. In a Fire, close all doors behind you!\n" +  "\n" +
                    "2. Keep Fire and Smoke Out of Building Hallways and Stairs.\n" +  "\n" +
                    "3. Protect your Neighbours and your Home!";  }
            case 4 :  { return "Call 911 Fire/Police/Medical";  }
        }
        return "End of Instructions";
    }

    /**
     * Dynamically change the text(instructions) upon button click
     * @param view
     */
    public void PreviousStep(View view){
        if(j > 0 )
        {
            j--;
        }
        String s = getTxt(j);
        instruction.setText(s);
    }

    /**
     * Dynamically change the text(instructions) upon button click
     * @param view
     */
    public void nextStep(View view) {
        if(j < 5 )
        {
            j++;
        }
        String s = getTxt(j);
        instruction.setText(s);
    }
}