package com.example.nazarkorchak.first;

public class Friend {

    private String firstName,lastName,Photo;
    private int userID;

    public Friend(String firstName, String lastName, String photo, int userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        Photo = photo;
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
