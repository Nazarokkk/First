package com.example.nazarkorchak.first;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.nazarkorchak.first.api.api;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyApplication extends Application
{
    private List<Friend> friendList;

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the singletons so their instances
        // are bound to the application process.
        //initSingletons();
    }

    protected void initSingletons()
    {
        // Initialize the instance of MySingleton
        //MySingleton.initInstance();
    }

    public void customAppMethod()
    {
        // Custom application method
    }
}
