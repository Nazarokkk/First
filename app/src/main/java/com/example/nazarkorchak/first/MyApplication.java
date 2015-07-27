package com.example.nazarkorchak.first;

import android.app.Application;
import android.util.Log;

import com.example.nazarkorchak.first.api.VkApi;
import com.example.nazarkorchak.first.events.AlbumEvent;
import com.example.nazarkorchak.first.events.FriendsEvent;
import com.example.nazarkorchak.first.events.LoadAlbumData;
import com.example.nazarkorchak.first.events.LoadFriendsData;
import com.example.nazarkorchak.first.events.LoadPhotoData;
import com.example.nazarkorchak.first.events.PhotoEvent;
import com.example.nazarkorchak.first.model.AlbumImage;
import com.example.nazarkorchak.first.model.Friend;
import com.example.nazarkorchak.first.model.Photo;
import com.example.nazarkorchak.first.model.TokenHolder;
import com.example.nazarkorchak.first.responses.AlbumResponse;
import com.example.nazarkorchak.first.responses.FriendsListResponse;
import com.example.nazarkorchak.first.responses.LoadAlbumResponse;
import com.example.nazarkorchak.first.responses.LoadPhotosResponse;
import com.example.nazarkorchak.first.responses.PhotosResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;


public class MyApplication extends Application {

    private VkApi myApi;

    private List<Photo> photoList;


    @Override
    public void onCreate() {
        super.onCreate();

        EventBus.getDefault().register(this);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.vk.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        myApi = restAdapter.create(VkApi.class);

    }

    public void onEventAsync(FriendsEvent event) {

        // TODO Use params from event
        // request template https://api.vk.com/method/friends.get?user_id=13140554&fields=photo_100
        Map<String, String> mMap = new HashMap<>();


        mMap.put("user_id", TokenHolder.getUserID(getApplicationContext()));
        mMap.put("fields", "photo_100");


        FriendsListResponse friendsListResponse = myApi.getFriendList(mMap);
        List<Friend> friendList = friendsListResponse.getResponse();

        Log.e("LOG",friendList.get(0).toString());

        EventBus.getDefault().post(new LoadFriendsData(friendList));

    }

    public void onEventAsync(AlbumEvent event) {
        Map<String, String> mMap = new HashMap<>();

        mMap.put("owner_id", String.valueOf(event.message));
        mMap.put("need_system", "1");
        mMap.put("need_covers", "1");
        mMap.put("v", "5.34");

        AlbumResponse albumResponse = myApi.getAlbums(mMap);
        LoadAlbumResponse loadAlbumResponse = albumResponse.getResponse();
        List<AlbumImage> albumImageList = loadAlbumResponse.getItems();

        EventBus.getDefault().post(new LoadAlbumData(albumImageList));
    }


    public void onEventAsync(PhotoEvent event) {


        Map<String, String> mMap = new HashMap<>();

        mMap.put("owner_id", String.valueOf(event.friendID));
        mMap.put("album_id", String.valueOf(event.albumID));
        mMap.put("v", "5.34");
        mMap.put("access_token", TokenHolder.getToken(getApplicationContext()));


        PhotosResponse photosResponse = myApi.getPhotos(mMap);
        LoadPhotosResponse loadPhotosResponse = photosResponse.getResponse();
        photoList = loadPhotosResponse.getItems();

        EventBus.getDefault().post(new LoadPhotoData(photoList));

    }


    @Override
    public void onTerminate() {
        EventBus.getDefault().unregister(this);
        super.onTerminate();
    }

}
