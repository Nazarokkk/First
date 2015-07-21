package com.example.nazarkorchak.first.api;

import android.app.DownloadManager;

import com.example.nazarkorchak.first.Friend;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface api {

        @GET("/users/{username}")
        void getUser(@Path("username") String username, Callback<Friend> cb);

        @GET("/friends.get?user_id={userID}&order=random&fields=first_name,last_name,photo_100&version=5.34")
        void getFriendList(@Query("userID") String userID, Callback<List<Friend>> cb);



}
