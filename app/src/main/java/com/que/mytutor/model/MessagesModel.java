package com.que.mytutor.model;

public class MessagesModel {
    private String Date_Time;
    private String Message;
    private String Id;

    public MessagesModel(String date_Time, String message, String id) {
        Date_Time = date_Time;
        Message = message;
        Id = id;
    }

    public MessagesModel() {
    }

    public String getDate_Time() {
        return Date_Time;
    }

    public void setDate_Time(String date_Time) {
        Date_Time = date_Time;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
