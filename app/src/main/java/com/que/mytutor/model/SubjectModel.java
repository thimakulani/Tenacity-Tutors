package com.que.mytutor.model;

public class SubjectModel {
    private String subject;
    private String id;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SubjectModel(String subject, String id) {
        this.subject = subject;
        this.id = id;
    }

    public SubjectModel() {
    }
}
