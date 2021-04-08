package com.que.mytutor.model;

public class Appointment {
    private String MentorId;
    private String Id;
    private String TimeDate;
    private String Status;

    public Appointment(String mentorId, String id, String timeDate, String status) {
        MentorId = mentorId;
        Id = id;
        TimeDate = timeDate;
        Status = status;
    }

    public Appointment() {
    }

    public String getMentorId() {
        return MentorId;
    }

    public void setMentorId(String mentorId) {
        MentorId = mentorId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTimeDate() {
        return TimeDate;
    }

    public void setTimeDate(String timeDate) {
        TimeDate = timeDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
