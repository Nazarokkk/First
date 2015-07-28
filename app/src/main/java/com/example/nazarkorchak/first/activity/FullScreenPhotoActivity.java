package com.example.nazarkorchak.first.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nazarkorchak.first.Adapters.PhotoPagerAdapter;
import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.events.SendImagesEvent;
import com.example.nazarkorchak.first.model.Photo;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class FullScreenPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_photo);

        int photoPosition = getIntent().getIntExtra("position", -1);
        ArrayList<String> photoList = getIntent().getStringArrayListExtra("list");


        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        PhotoPagerAdapter ssAdapter = new PhotoPagerAdapter(this,photoList);
        viewPager.setAdapter(ssAdapter);
        viewPager.setCurrentItem(photoPosition,true);


    }
}
