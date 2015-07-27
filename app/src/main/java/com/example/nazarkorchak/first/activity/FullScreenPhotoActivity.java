package com.example.nazarkorchak.first.activity;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.events.SendImages;
import com.example.nazarkorchak.first.model.Photo;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class FullScreenPhotoActivity extends Activity {

    ArrayList<Photo> photoList = new ArrayList<Photo>();

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    public void onEvent(SendImages event) {
        photoList.addAll(event.photoList);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_photo);

        ImageView imageView = (ImageView) findViewById(R.id.full_screen_photo);


        int photoPosition = getIntent().getIntExtra("position", -1);

       // ArrayList<Photo> photoList = (ArrayList<Photo>) getIntent().getSerializableExtra("imageList");

        Glide.with(this).load(photoList.get(photoPosition).getPhoto_604()).into(imageView);
        // imageView.setImageResource(R.drawable.img);

    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
