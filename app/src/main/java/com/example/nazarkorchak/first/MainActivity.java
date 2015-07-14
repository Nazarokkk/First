package com.example.nazarkorchak.first;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import de.greenrobot.event.EventBus;

public class MainActivity extends ActionBarActivity {


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



    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


}
