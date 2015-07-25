package com.example.nazarkorchak.first.events;

import com.example.nazarkorchak.first.model.Friend;

import java.util.List;

/**
 * Created by i.demedyuk on 25.07.2015.
 */
public class FriendsListResponse {
    private List<Friend> listFriend;

    public FriendsListResponse(List<Friend> listFriend) {
        this.listFriend = listFriend;
    }

    public List<Friend> getListFriend() {
        return listFriend;
    }
}
