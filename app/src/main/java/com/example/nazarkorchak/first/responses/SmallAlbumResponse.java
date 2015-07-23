package com.example.nazarkorchak.first.responses;


import com.example.nazarkorchak.first.model.AlbumImage;

import java.util.List;

public class SmallAlbumResponse {
    private int count;
    private List<AlbumImage> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<AlbumImage> getItems() {
        return items;
    }

    public void setItems(List<AlbumImage> items) {
        this.items = items;
    }
}
