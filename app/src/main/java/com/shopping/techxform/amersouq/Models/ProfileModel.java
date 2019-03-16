package com.shopping.techxform.amersouq.Models;

public class ProfileModel {

    public ProfileModel(String name, Integer image) {
        this.name = name;
        this.image = image;
    }

    private String name;
    private Integer image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
