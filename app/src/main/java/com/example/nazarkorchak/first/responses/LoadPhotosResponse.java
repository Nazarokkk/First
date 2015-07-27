package com.example.nazarkorchak.first.responses;

import com.example.nazarkorchak.first.model.Photo;

import java.util.List;


public class LoadPhotosResponse {

    private int count;
    private List<Photo> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Photo> getItems() {
        return items;
    }

    public void setItems(List<Photo> items) {
        this.items = items;
    }
}
