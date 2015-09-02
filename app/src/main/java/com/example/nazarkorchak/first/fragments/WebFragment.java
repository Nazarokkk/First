package com.example.nazarkorchak.first.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.events.MessageEvent;
import com.example.nazarkorchak.first.model.TokenHolder;

import de.greenrobot.event.EventBus;

public class WebFragment extends Fragment {

    private static final String TAG = "WebFragment";


    final String mURL = "https://oauth.vk.com/authorize?client_id=4980525&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,photos,offline&revoke=1&display=mobile&response_type=token ";
    final String ACCESS_TOKEN = "access_token";
    final String EXPIRES_IN = "expires_in";
    final String USER_ID = "user_id";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        WebView myWebView = (WebView) view.findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl(mURL.trim());

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().hide();



        return view;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Log.d(TAG, url);

            if (url.contains(ACCESS_TOKEN)) {

                url = url.replace("#", "?");

                Uri token_uri = Uri.parse(url);


                String accessToken = token_uri.getQueryParameter(ACCESS_TOKEN);
                String expiresIn = token_uri.getQueryParameter(EXPIRES_IN);
                String userId = token_uri.getQueryParameter(USER_ID);

                Log.d(TAG, "TOKEN = " + accessToken);
                Log.d(TAG, "TIME = " + expiresIn);
                Log.d(TAG, "ID = " + userId);


                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(ACCESS_TOKEN, accessToken);
                editor.putString(EXPIRES_IN, expiresIn);
                editor.putString(USER_ID, userId);
                editor.commit();


                TokenHolder.setUserID(getActivity(), userId);
                TokenHolder.setToken(getActivity(), accessToken);

                EventBus.getDefault().post(new MessageEvent());

                return true;
            }

            return false;
        }
    }

}
