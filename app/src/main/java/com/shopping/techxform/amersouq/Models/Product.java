package com.shopping.techxform.amersouq.Models;

import java.util.ArrayList;

public class Product {

    private String name;

    private String imageUrl;

    private String ratings;

    private double actualPrice;

    private String reviews;

    private double discount;

    private ArrayList<String> imagesList;

    public Product(String name, String imageUrl,  double actualPrice,double discount,String ratings, String reviews,ArrayList<String> imagesList) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.ratings = ratings;
        this.actualPrice = actualPrice;
        this.reviews = reviews;
        this.discount = discount;
        this.imagesList = imagesList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public ArrayList<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(ArrayList<String> imagesList) {
        this.imagesList = imagesList;
    }
}
