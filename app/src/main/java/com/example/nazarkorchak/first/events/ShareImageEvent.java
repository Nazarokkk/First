package com.example.nazarkorchak.first.events;

import android.net.Uri;


public class ShareImageEvent {
    public Uri message;
    public boolean isShowToolBar;

    public ShareImageEvent(Uri message, boolean isShowToolBar) {
        this.message = message;
        this.isShowToolBar = isShowToolBar;
    }
}
