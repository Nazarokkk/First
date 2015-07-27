package com.example.nazarkorchak.first.events;

import com.example.nazarkorchak.first.model.Photo;

import java.util.ArrayList;


public class SendImagesEvent {

    public ArrayList<Photo> photoList;

    public SendImagesEvent(ArrayList<Photo> photoList) {
        this.photoList = photoList;
    }
}
