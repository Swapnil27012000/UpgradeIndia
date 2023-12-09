package com.example.upgradeindia.Model;

public class User {
    private String id;
    private String username;
    private String fullname;
    private String imagurl;
    private String bio;

    public User(String id, String username, String fullname, String imagurl, String bio) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.imagurl = imagurl;
        this.bio = bio;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImagurl() {
        return imagurl;
    }

    public void setImagurl(String imagurl) {
        this.imagurl = imagurl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
