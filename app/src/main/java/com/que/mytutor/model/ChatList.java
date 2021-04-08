package com.que.mytutor.model;

public class ChatList {
    private String names;
    private String img_url;
    private String id;

    public ChatList(String names, String img_url, String id) {
        this.names = names;
        this.img_url = img_url;
        this.id = id;
    }

    public ChatList() {
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
