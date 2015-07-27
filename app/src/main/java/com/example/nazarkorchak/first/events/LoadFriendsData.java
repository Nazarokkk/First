package com.example.nazarkorchak.first.events;


import com.example.nazarkorchak.first.model.Friend;

import java.util.List;

public class LoadFriendsData {
    public List<Friend> friendList;

    public LoadFriendsData(List<Friend> friendList) {
        this.friendList = friendList;
    }
}
