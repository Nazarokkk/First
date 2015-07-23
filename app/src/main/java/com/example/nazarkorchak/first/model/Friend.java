package com.example.nazarkorchak.first.model;

public class Friend {

    private String first_name,last_name,photo_100;
    private int user_id;

    public Friend(String first_name, String last_name, String photo_100, int user_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.photo_100 = photo_100;
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhoto_100() {
        return photo_100;
    }

    public void setPhoto_100(String photo_100) {
        this.photo_100 = photo_100;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
