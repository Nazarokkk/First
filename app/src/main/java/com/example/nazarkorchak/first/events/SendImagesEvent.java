package com.example.nazarkorchak.first.events;

import com.example.nazarkorchak.first.model.Photo;

import java.util.List;


public class SendImagesEvent {

    public List<Photo> photoList;

    public SendImagesEvent(List<Photo> photoList) {
        this.photoList = photoList;
    }
}
