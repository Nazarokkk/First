package com.example.nazarkorchak.first.api;

import com.example.nazarkorchak.first.MResponse;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface api {

    @GET("/method/friends.get")
    MResponse getFriendList(@QueryMap Map<String, String> queryMap);


}
