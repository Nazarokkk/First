package com.example.nazarkorchak.first.events;

import com.example.nazarkorchak.first.model.Photo;

import java.util.List;


public class SendImages {

    public List<Photo> photoList;

    public SendImages(List<Photo> photoList) {
        this.photoList = photoList;
    }
}
