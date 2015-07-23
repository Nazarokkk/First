package com.example.nazarkorchak.first.api;

import com.example.nazarkorchak.first.responses.BigAlbumResponse;
import com.example.nazarkorchak.first.responses.MySmallResponse;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface api {

    @GET("/method/friends.get")
    MySmallResponse getFriendList(@QueryMap Map<String, String> queryMap);

    @GET("/method/photos.getAlbums")
    BigAlbumResponse getAlbums(@QueryMap Map<String, String> queryMap);

}
