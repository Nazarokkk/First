package com.example.nazarkorchak.first.events;


import com.example.nazarkorchak.first.model.Friend;

import java.util.List;

public class LoadFriendsData {

    public List<Friend> listFriend;

    public LoadFriendsData(){}

    public LoadFriendsData(List<Friend> listFriend) {
        this.listFriend = listFriend;
    }

}
