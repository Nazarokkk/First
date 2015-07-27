package com.example.nazarkorchak.first.api;

import com.example.nazarkorchak.first.responses.AlbumResponse;
import com.example.nazarkorchak.first.responses.FriendsListResponse;
import com.example.nazarkorchak.first.responses.PhotosResponse;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;


public interface VkApi {

    @GET("/method/friends.get")
    FriendsListResponse getFriendList(@QueryMap Map<String, String> queryMap);

    @GET("/method/photos.getAlbums")
    AlbumResponse getAlbums(@QueryMap Map<String, String> queryMap);

    @GET("/method/photos.get")
    PhotosResponse getPhotos(@QueryMap Map<String, String> queryMap);


}
