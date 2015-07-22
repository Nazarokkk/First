package com.example.nazarkorchak.first.api;

import com.example.nazarkorchak.first.MResponse;
import com.example.nazarkorchak.first.MySmallResponse;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface api {

    @GET("/method/friends.get")
    MySmallResponse getFriendList(@QueryMap Map<String, String> queryMap);
}
