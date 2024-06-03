package com.example.hope;

public class FriendModel {
    private String location;
    private String date;
    private String message;

    public FriendModel(String location, String date, String message) {
        this.location = location;
        this.date = date;
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
