package com.example.nazarkorchak.first;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.example.nazarkorchak.first.events.AlbumEvent;
import com.example.nazarkorchak.first.events.MessageEvent;
import com.example.nazarkorchak.first.fragments.AlbumFragment;
import com.example.nazarkorchak.first.fragments.FriendsListFragment;
import com.example.nazarkorchak.first.fragments.WebFragment;

import de.greenrobot.event.EventBus;

public class MainActivity extends ActionBarActivity {

    //final String getFriends = "https://api.vk.com/method/friends.get?user_id=134487854&order=random&fields=first_name,last_name,photo_100&version=5.34";
    //final String getAlbums = "https://api.vk.com/method/photos.getAlbums?owner_id=134487854&need_system=1&need_covers=1&v=5.34";

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
    }


    public void onEvent(MessageEvent event) {
        getFragmentManager().beginTransaction().replace(R.id.FragmentContainer, new FriendsListFragment()).commit();
    }

    public void onEvent(AlbumEvent event) {
        getFragmentManager().beginTransaction().replace(R.id.FragmentContainer, new AlbumFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


}
