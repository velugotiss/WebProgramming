package com.example.vijaya.androidhardware;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.vijaya.androidhardware.R;

public class MainActivity extends AppCompatActivity {
    Intent redirect = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLocationClick(View view) {
        redirect = new Intent(MainActivity.this,LocationActivity.class);
        startActivity(redirect);
    }

    public void onPhotoClick(View view) {
        redirect = new Intent(MainActivity.this,CameraActivity.class);
        startActivity(redirect);
    }

    public void onAudioClick(View view) {
        redirect = new Intent(MainActivity.this,AudioRecordingActivity.class);
        startActivity(redirect);
    }

    public void onStorageClick(View view) {
        redirect = new Intent(MainActivity.this,StorageActivity.class);
        startActivity(redirect);
    }
}