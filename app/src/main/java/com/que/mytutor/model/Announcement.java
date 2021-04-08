package com.que.mytutor.model;

public class Announcement {
    private String date_time;
    private String announcement;

    public Announcement(String date_time, String announcement) {
        this.date_time = date_time;
        this.announcement = announcement;
    }

    public Announcement() {
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
}
