package com.que.mytutor.model;

public class TutorsModel {
    private String name;
    private String surname;
    private String id;
    private String phone;
    private String role;
    private String email;
    private String imgUrl;
    private String subjects;

    public TutorsModel(String name, String surname, String id, String phone, String role, String email, String imgUrl, String subjects) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.phone = phone;
        this.role = role;
        this.email = email;
        this.imgUrl = imgUrl;
        this.subjects = subjects;
    }


    public TutorsModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }
}
