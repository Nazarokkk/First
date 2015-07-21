package com.example.nazarkorchak.first;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.nazarkorchak.first.api.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;


public class MyApplication extends Application
{
    private List<Friend> friendList;

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


        mMap.put("user_id","134487854");
        mMap.put("order","random");
        mMap.put("fields","photo_100");
        mMap.put("version","5.34");

        MResponse bigResponse = myApi.getFriendList(mMap);
        MySmallResponse mySmallResponse = bigResponse.getResponse();
        List<Friend> list = mySmallResponse.getItem();


        Log.d("List",list.get(0).toString());


    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        EventBus.getDefault().unregister(this);
    }
}
