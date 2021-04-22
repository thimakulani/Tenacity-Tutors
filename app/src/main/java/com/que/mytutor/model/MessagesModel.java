package com.que.mytutor.model;

public class MessagesModel {
    private String dates;
    private String text;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public MessagesModel(String date_Time, String message, String uid) {
        dates = date_Time;
        text = message;
        this.uid = uid;
    }

    public MessagesModel() {
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
