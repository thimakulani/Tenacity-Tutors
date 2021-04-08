package com.que.mytutor.model;

public class Mentors {
    private String Names;
    private String Surname;
    private String Id;
    private String Gender;



    private String ImgUrl;
    private String Description;

    public Mentors() {
    }

    public Mentors(String names, String surname, String id, String gender, String imgUrl, String description) {
        Names = names;
        Surname = surname;
        Id = id;
        Gender = gender;
        ImgUrl = imgUrl;
        Description = description;

    }
    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
    public String getNames() {
        return Names;
    }

    public void setNames(String names) {
        Names = names;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
