package com.example.nazarkorchak.first.api;

import com.example.nazarkorchak.first.responses.BigAlbumResponse;
import com.example.nazarkorchak.first.responses.LoadFriendsResponse;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface VkApi {

    @GET("/method/friends.get")
    LoadFriendsResponse getFriendList(@QueryMap Map<String, String> queryMap);

    @GET("/method/photos.getAlbums")
    BigAlbumResponse getAlbums(@QueryMap Map<String, String> queryMap);

}
