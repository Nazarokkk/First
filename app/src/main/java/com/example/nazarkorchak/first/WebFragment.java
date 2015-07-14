package com.example.nazarkorchak.first;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import de.greenrobot.event.EventBus;

public class WebFragment extends Fragment {

    private static final String TAG = "WebFragment";

    // TODO use constants not variables
    String mURL = "https://oauth.vk.com/authorize?client_id=4980525&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,photos&revoke=1&display=mobile&response_type=token ";
    String REDIRECT_URI = "access_token";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.webfragment, container, false);

        WebView myWebView = (WebView) view.findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl(mURL.trim());


        return view;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Log.d(TAG, url);

            if (url.contains(REDIRECT_URI)) {  // TODO use startWith not contains

                url = url.replace("#", "?");

                Uri token_uri = Uri.parse(url);


                String accessToken = token_uri.getQueryParameter("access_token");
                String expiresIn = token_uri.getQueryParameter("expires_in");
                String userId = token_uri.getQueryParameter("user_id");

                Log.d(TAG, "TOKEN = " + accessToken);
                Log.d(TAG, "TIME = " + expiresIn);
                Log.d(TAG, "ID = " + userId);


                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("access_token", accessToken); // TODO extract preference KEYs to constants
                editor.putString("expires_in", expiresIn);
                editor.putString("user_id", userId);
                editor.commit();

                Log.d("EDITOR", "TOKEN_IN_EDITOR = " + sharedPref.getString("access_token", "nothing")); // TODO extract preference KEYs to constants
                Log.d("EDITOR", "TIME_IN_EDITOR = " + sharedPref.getString("expires_in", "nothing"));
                Log.d("EDITOR", "ID_IN_EDITOR = " + sharedPref.getString("user_id", "nothing"));


                EventBus.getDefault().post(new MessageEvent());

                return true;
            }

            return false;
        }
    }

}
