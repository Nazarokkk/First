package com.example.nazarkorchak.first;

import android.app.Application;

import com.example.nazarkorchak.first.api.VkApi;
import com.example.nazarkorchak.first.events.AlbumEvent;
import com.example.nazarkorchak.first.events.FriendsListResponse;
import com.example.nazarkorchak.first.events.LoadAlbumData;
import com.example.nazarkorchak.first.events.LoadFriendsData;
import com.example.nazarkorchak.first.model.AlbumImage;
import com.example.nazarkorchak.first.model.Friend;
import com.example.nazarkorchak.first.responses.BigAlbumResponse;
import com.example.nazarkorchak.first.responses.LoadFriendsResponse;
import com.example.nazarkorchak.first.responses.SmallAlbumResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;


public class MyApplication extends Application
{
    private VkApi myApi;

    @Override
    public void onCreate() {
        super.onCreate();

        EventBus.getDefault().register(this);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://VkApi.vk.com")
                .build();

        myApi = restAdapter.create(VkApi.class);

    }

    public void onEventAsync(LoadFriendsData event) {

        // TODO Use params from event
        // request template https://api.vk.com/method/friends.get?user_id=13140554&fields=photo
        Map<String,String> mMap = new HashMap<>();

            mMap.put("user_id", "134487854");
            mMap.put("order", "random");
            mMap.put("fields", "photo_100");
            mMap.put("version", "5.34");

            LoadFriendsResponse loadFriendsResponse = myApi.getFriendList(mMap);
            List<Friend> friendList = loadFriendsResponse.getResponse();

            EventBus.getDefault().post(new FriendsListResponse(friendList));

    }

    public void onEventAsync(AlbumEvent event) {
        Map<String,String> mMap = new HashMap<>();

        mMap.put("owner_id", String.valueOf(event.message));
        mMap.put("need_system", "1");
        mMap.put("need_covers", "1");
        mMap.put("v", "5.34");

        BigAlbumResponse bigAlbumResponse = myApi.getAlbums(mMap);
        SmallAlbumResponse smallAlbumResponse = bigAlbumResponse.getResponse();
        List<AlbumImage> albumImageList = smallAlbumResponse.getItems();

        EventBus.getDefault().post(new LoadAlbumData(albumImageList));
    }
}
