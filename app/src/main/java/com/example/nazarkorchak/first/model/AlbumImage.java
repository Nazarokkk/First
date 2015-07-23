package com.example.nazarkorchak.first.model;


public class AlbumImage {

    private String thumb_src, title;
    private int id;

    public AlbumImage(String thumb_src, String title, int id) {
        this.thumb_src = thumb_src;
        this.title = title;
        this.id = id;
    }

    public String getThumb_src() {
        return thumb_src;
    }

    public void setThumb_src(String thumb_src) {
        this.thumb_src = thumb_src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
