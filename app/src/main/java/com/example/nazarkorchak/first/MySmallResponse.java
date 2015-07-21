package com.example.nazarkorchak.first;

import java.util.List;

public class MySmallResponse {

    private int count;
    private List<Friend> item;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Friend> getItem() {
        return item;
    }

    public void setItem(List<Friend> item) {
        this.item = item;
    }
}
