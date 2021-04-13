package com.que.mytutor.model;

public class Announcement {
    private String datetime;
    private String message;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Announcement(String datetime, String message, String id) {
        this.datetime = datetime;
        this.message = message;
        this.id = id;
    }

    public Announcement() {
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
