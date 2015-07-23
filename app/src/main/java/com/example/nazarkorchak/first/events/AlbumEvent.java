package com.example.nazarkorchak.first.events;


import com.example.nazarkorchak.first.model.AlbumImage;

import java.util.List;

public class AlbumEvent {

    public int message;
    public List<AlbumImage> albumImageList;

    public AlbumEvent() {
    }

    public AlbumEvent(List<AlbumImage> albumImageList) {
        this.albumImageList = albumImageList;
    }

    public AlbumEvent(int message) {
        this.message = message;
    }
}
