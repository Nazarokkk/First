package com.example.nazarkorchak.first.events;


public class PhotoEvent {

    public String friendID;
    public int albumID;

    public PhotoEvent() {
    }

    public PhotoEvent(String friendID, int albumID) {
        this.friendID = friendID;
        this.albumID = albumID;
    }
}
