package com.example.nazarkorchak.first.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.events.AlbumEvent;
import com.example.nazarkorchak.first.events.MessageEvent;
import com.example.nazarkorchak.first.events.PhotoEvent;
import com.example.nazarkorchak.first.events.SendSearchQueryEvent;
import com.example.nazarkorchak.first.fragments.AlbumFragment;
import com.example.nazarkorchak.first.fragments.FriendsListFragment;
import com.example.nazarkorchak.first.fragments.PhotoFrament;
import com.example.nazarkorchak.first.fragments.WebFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    //final String getFriends = "https://api.vk.com/method/friends.get?user_id=134487854&order=random&fields=first_name,last_name,photo_100&version=5.34";
    //final String getAlbums = "https://api.vk.com/method/photos.getAlbums?owner_id=134487854&need_system=1&need_covers=1&v=5.34";
    //final String getPhotos = "https://api.vk.com/method/photos.get?owner_id=134487854&album_id=-6&access_token=acc411f13c8a4680a56509a0e38721e5b380439866f4c58a6bdf8b50b4fb896a6779164e4e353816ba884&v=5.34";
    //final String getPhotos = "https://api.vk.com/method/users.get?fields=sex,bdate,city,country,photo_50,photo_100,photo_200_orig,photo_200,photo_400_orig,photo_max&access_token=acc411f13c8a4680a56509a0e38721e5b380439866f4c58a6bdf8b50b4fb896a6779164e4e353816ba884&v=5.34";

    FragmentManager fragmentManager = getFragmentManager();
    Fragment fragment;
    boolean isTablet;

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

        if (mActionBarToolbar != null) {
            setSupportActionBar(mActionBarToolbar);
        }

        FrameLayout fragManager = (FrameLayout) findViewById(R.id.FragmentContainer1);

        isTablet = (fragManager != null);

//        Drawer result = new DrawerBuilder()
//                .withActivity(this)
//                .withToolbar(mActionBarToolbar)
//                .withDisplayBelowStatusBar(true)
//                .withActionBarDrawerToggleAnimated(true)
//                .build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                EventBus.getDefault().post(new SendSearchQueryEvent(newText));
                newText = null;
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onEvent(MessageEvent event) {
        getFragmentManager().beginTransaction().replace(R.id.FragmentContainer, new FriendsListFragment()).commit();
        invalidateOptionsMenu();
    }

    public void onEvent(AlbumEvent event) {
        if (isTablet) {
            getFragmentManager().beginTransaction().replace(R.id.FragmentContainer1, new AlbumFragment()).commit();
            invalidateOptionsMenu();
        } else {
            getFragmentManager().beginTransaction()
                    .replace(R.id.FragmentContainer, new AlbumFragment())
                    .addToBackStack(null)
                    .commit();
            invalidateOptionsMenu();
        }
    }

    public void onEvent(PhotoEvent event) {
        if (isTablet) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.FragmentContainer1, new PhotoFrament())
                    .addToBackStack(null)
                    .commit();
            invalidateOptionsMenu();
        } else {
            getFragmentManager().beginTransaction()
                    .replace(R.id.FragmentContainer, new PhotoFrament())
                    .addToBackStack(null)
                    .commit();
            invalidateOptionsMenu();
        }
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStackImmediate();
            return;
        }
        super.onBackPressed();

    }

}
