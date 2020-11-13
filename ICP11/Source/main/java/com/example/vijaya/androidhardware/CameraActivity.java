package com.example.vijaya.androidhardware;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vijaya.androidhardware.R;

public class CameraActivity extends AppCompatActivity {
    int TAKE_PHOTO_CODE = 0;
    ImageView userImage;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    public static final int CAMERA_REQ = 01;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

    public void openCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        System.out.println("In Open Camera method");
//        startActivityForResult(intent, CAMERA_REQ);
        try {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQ) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ImageView imgV = findViewById(R.id.imgView);
            imgV.setImageBitmap(bitmap);
        }
    }
}
