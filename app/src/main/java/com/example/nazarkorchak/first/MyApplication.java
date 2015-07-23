package com.example.nazarkorchak.first;

import android.app.Application;

import com.example.nazarkorchak.first.api.api;
import com.example.nazarkorchak.first.events.AlbumEvent;
import com.example.nazarkorchak.first.events.LoadAlbumData;
import com.example.nazarkorchak.first.events.LoadFriendsData;
import com.example.nazarkorchak.first.model.AlbumImage;
import com.example.nazarkorchak.first.model.Friend;
import com.example.nazarkorchak.first.responses.BigAlbumResponse;
import com.example.nazarkorchak.first.responses.MySmallResponse;
import com.example.nazarkorchak.first.responses.SmallAlbumResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;


public class MyApplication extends Application
{
    private List<Friend> friendList;
    private List<AlbumImage> albumImageList;

    api myApi;

    @Override
    public void onCreate() {
        super.onCreate();

        EventBus.getDefault().register(this);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.vk.com")
                .build();

        myApi = restAdapter.create(api.class);

    }

    public void onEventAsync(LoadFriendsData event) {

        Map<String,String> mMap = new HashMap<>();


            mMap.put("user_id", "134487854");
            mMap.put("order", "random");
            mMap.put("fields", "photo_100");
            mMap.put("version", "5.34");

            MySmallResponse mySmallResponse = myApi.getFriendList(mMap);
            friendList = mySmallResponse.getResponse();

            EventBus.getDefault().post(new LoadFriendsData(friendList));

    }


    public void onEventAsync(AlbumEvent event) {


        Map<String,String> mMap = new HashMap<>();

        mMap.put("owner_id", String.valueOf(event.message));
        mMap.put("need_system", String.valueOf(1));
        mMap.put("need_covers", String.valueOf(1));
        mMap.put("v", "5.34");


        BigAlbumResponse bigAlbumResponse = myApi.getAlbums(mMap);
        SmallAlbumResponse smallAlbumResponse = bigAlbumResponse.getResponse();
        albumImageList = smallAlbumResponse.getItems();

        EventBus.getDefault().post(new LoadAlbumData(albumImageList));

    }



    @Override
    public void onTerminate() {
        EventBus.getDefault().unregister(this);
        super.onTerminate();
    }

}
