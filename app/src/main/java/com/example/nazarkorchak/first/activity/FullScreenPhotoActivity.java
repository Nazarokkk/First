package com.example.nazarkorchak.first.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.nazarkorchak.first.Adapters.PhotoPagerAdapter;
import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.events.ShareImageEvent;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class FullScreenPhotoActivity extends AppCompatActivity {

    private ShareActionProvider mShareActionProvider;
    ViewPager viewPager;
    ArrayList<String> photoList;

    Uri bmpUri;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_photo);

        int photoPosition = getIntent().getIntExtra("position", -1);
        photoList = getIntent().getStringArrayListExtra("list");


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        PhotoPagerAdapter ssAdapter = new PhotoPagerAdapter(this, photoList);
        viewPager.setAdapter(ssAdapter);
        viewPager.setCurrentItem(photoPosition, true);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_fullscreen);

        if (mActionBarToolbar != null) {
            setSupportActionBar(mActionBarToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().hide();
        }

    }


    private void setShareIntent(Intent shareIntent) {
        mShareActionProvider.setShareIntent(shareIntent);
    }


    public void onEvent(ShareImageEvent event) {
        if (event.message != null) {
            bmpUri = event.message;
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            shareIntent.setType("image/*");
            setShareIntent(shareIntent);
        }

        if (event.isShowToolBar) {
            getSupportActionBar().show();
        } else {
            getSupportActionBar().hide();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_fullscreen, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);

        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
