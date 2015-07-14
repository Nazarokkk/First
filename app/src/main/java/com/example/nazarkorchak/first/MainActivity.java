package com.example.nazarkorchak.first;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        Fragment fragment;

        if(sharedPref.getString("access_token", "nothing").equals("nothing")) // TODO move hardcoded strings to constants
        {
            fragment = new WebFragment();
        }
        else {
            fragment = new FriendsListFragment();

        }

        //add a fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.FragmentContainer, fragment).commit();
    }


}
