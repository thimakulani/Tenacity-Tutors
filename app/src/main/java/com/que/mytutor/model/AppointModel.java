package com.que.mytutor.model;

public class AppointModel {
    private String tutor_id;
    private String id;
    private String time;
    private String date;
    private String stud_id;
    private String status;
    private String subject;
    private String grade;



    private String meeting_room;





    public AppointModel() {
    }



    public AppointModel(String tutor_id, String id, String time, String date, String stud_id, String status, String subject, String grade, String meeting_room) {
        this.tutor_id = tutor_id;
        this.id = id;
        this.time = time;
        this.date = date;
        this.stud_id = stud_id;
        this.status = status;
        this.subject = subject;
        this.grade = grade;
        this.meeting_room = meeting_room;
    }
    public String getMeeting_room() {
        return meeting_room;
    }

    public void setMeeting_room(String meeting_room) {
        this.meeting_room = meeting_room;
    }
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(String tutor_id) {
        this.tutor_id = tutor_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStud_id() {
        return stud_id;
    }

    public void setStud_id(String stud_id) {
        this.stud_id = stud_id;
    }
}
