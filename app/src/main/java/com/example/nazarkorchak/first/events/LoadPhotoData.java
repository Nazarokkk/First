package com.example.nazarkorchak.first.events;

import com.example.nazarkorchak.first.model.AlbumImage;
import com.example.nazarkorchak.first.model.Photo;

import java.util.List;


public class LoadPhotoData {

    public List<Photo> listPhoto;


    public LoadPhotoData(List<Photo> listPhoto) {
        this.listPhoto = listPhoto;
    }
}
