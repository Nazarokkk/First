package com.example.nazarkorchak.first.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.events.AlbumEvent;
import com.example.nazarkorchak.first.events.MessageEvent;
import com.example.nazarkorchak.first.events.PhotoEvent;
import com.example.nazarkorchak.first.fragments.AlbumFragment;
import com.example.nazarkorchak.first.fragments.FriendsListFragment;
import com.example.nazarkorchak.first.fragments.PhotoFrament;
import com.example.nazarkorchak.first.fragments.WebFragment;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    //final String getFriends = "https://api.vk.com/method/friends.get?user_id=134487854&order=random&fields=first_name,last_name,photo_100&version=5.34";
    //final String getAlbums = "https://api.vk.com/method/photos.getAlbums?owner_id=134487854&need_system=1&need_covers=1&v=5.34";
    //final String getPhotos = "https://api.vk.com/method/photos.get?owner_id=134487854&album_id=-6&access_token=acc411f13c8a4680a56509a0e38721e5b380439866f4c58a6bdf8b50b4fb896a6779164e4e353816ba884&v=5.34";

    FragmentManager fragmentManager = getFragmentManager();
    Fragment fragment;


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        if (sharedPref.getString("access_token", "nothing").equals("nothing")) // TODO move hardcoded strings to constants
        {
            fragment = new WebFragment();
        } else {
            fragment = new FriendsListFragment();
        }

        fragmentManager.beginTransaction()
                .replace(R.id.FragmentContainer, fragment).commit();

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);

        if(mActionBarToolbar != null) {
            setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    }


    public void onEvent(MessageEvent event) {
        getFragmentManager().beginTransaction().replace(R.id.FragmentContainer, new FriendsListFragment()).commit();
    }

    public void onEvent(AlbumEvent event) {
        getFragmentManager().beginTransaction().replace(R.id.FragmentContainer, new AlbumFragment())
                .addToBackStack(null)
                .commit();
    }

    public void onEvent(PhotoEvent event) {
        getFragmentManager().beginTransaction().replace(R.id.FragmentContainer, new PhotoFrament())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


}
