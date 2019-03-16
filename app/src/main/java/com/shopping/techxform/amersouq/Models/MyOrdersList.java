package com.shopping.techxform.amersouq.Models;

public class MyOrdersList {

    public MyOrdersList(String name, String status, Integer rating, Integer image) {
        this.name = name;
        this.status = status;
        this.rating = rating;
        this.image = image;
    }

    private String name;
    private String status;
    private Integer rating;
    private Integer image;

    public Integer getImgage() {
        return image;
    }

    public void setImgage(Integer image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
