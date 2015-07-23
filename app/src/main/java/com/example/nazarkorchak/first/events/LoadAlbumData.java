package com.example.nazarkorchak.first.events;


import com.example.nazarkorchak.first.model.AlbumImage;

import java.util.List;

public class LoadAlbumData {
    public List<AlbumImage> listAlbum;


    public LoadAlbumData(List<AlbumImage> listAlbum) {
        this.listAlbum = listAlbum;
    }
}
