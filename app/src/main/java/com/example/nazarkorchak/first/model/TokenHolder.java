package com.example.nazarkorchak.first.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class TokenHolder {

    final static String NAME_PREF_TOKEN = "access_token";
    final static String UserID = "user_id";
    final static String DefValue = "default";

    public static SharedPreferences getPrefs(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    public static void setUserID(Context context, String userID)
    {
        getPrefs(context).edit().putString(UserID, userID).apply();
    }

    public static void setToken(Context context, String token)
    {
        getPrefs(context).edit().putString(NAME_PREF_TOKEN, token).apply();
    }

    public static String getUserID(Context context)
    {
        return getPrefs(context).getString(UserID,DefValue);
    }

    public static String getToken(Context context)
    {
        return getPrefs(context).getString(NAME_PREF_TOKEN,DefValue);
    }



}
