package com.example.nazarkorchak.first;

import java.util.List;

public class MySmallResponse {

    private int count;
    private List<Friend> response;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Friend> getResponse() {
        return response;
    }

    public void setResponse(List<Friend> response) {
        this.response = response;
    }
}
