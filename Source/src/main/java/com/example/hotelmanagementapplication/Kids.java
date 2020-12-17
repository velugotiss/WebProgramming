package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kids extends AppCompatActivity {

    public static String selectedKidsFeatures="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids);

        String[] myArray = {"Need room near elevator/lower floor","Need handicap access","Noise-free surroundings","Avoid long queues","Medical emergencies"};
        List<String> fixedLenghtList = Arrays.asList(myArray);

//create object of listview holding all the locations list
        ListView listView=(ListView)findViewById(R.id.listOfServiceKidss);

        //create ArrayList of String
        final ArrayList<String> arrayList=new ArrayList<String>(fixedLenghtList);

//Create array Adapter and fill in the data in it, of the location list
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

//assign adapter to listview
        listView.setAdapter(arrayAdapter);

//add listener to listview

        Toast.makeText(Kids.this,"Click on all the extra servies needed",Toast.LENGTH_SHORT).show();

        //Displaying the list of locations of quakes along with the link attached to it
        //which directs the user to view more details.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = arrayList.get(i) + ": Adding it to your list";
                Snackbar.make(view, str, Snackbar.LENGTH_SHORT).show();
                String loc = arrayList.get(i);
                selectedKidsFeatures += loc;

            }
        });

    }
}