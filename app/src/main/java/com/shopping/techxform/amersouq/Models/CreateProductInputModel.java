package com.shopping.techxform.amersouq.Models;

public class CreateProductInputModel {

    private String product_name;
    private String short_description;
    private String full_description;
    private String featured_product;
    private String price;
    private String offer_price;
    private String condition;
    private String category_id;
    private String created_user_id;
    private String in_stock;
    private String start_datetime;
    private String end_datetime;

    public CreateProductInputModel(String product_name, String short_description, String full_description, String featured_product,
                                   String price, String offer_price, String condition, String category_id, String created_user_id, String in_stock,
                                   String start_datetime, String end_datetime) {
        this.product_name = product_name;
        this.short_description = short_description;
        this.full_description = full_description;
        this.featured_product = featured_product;
        this.price = price;
        this.offer_price = offer_price;
        this.condition = condition;
        this.category_id = category_id;
        this.created_user_id = created_user_id;
        this.in_stock = in_stock;
        this.start_datetime = start_datetime;
        this.end_datetime = end_datetime;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getStart_datetime() {
        return start_datetime;
    }

    public void setStart_datetime(String start_datetime) {
        this.start_datetime = start_datetime;
    }

    public String getEnd_datetime() {
        return end_datetime;
    }

    public void setEnd_datetime(String end_datetime) {
        this.end_datetime = end_datetime;
    }

    public String getTitle() {
        return product_name;
    }

    public void setTitle(String title) {
        this.product_name = title;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getFull_description() {
        return full_description;
    }

    public void setFull_description(String full_description) {
        this.full_description = full_description;
    }

    public String getFeatured_product() {
        return featured_product;
    }

    public void setFeatured_product(String featured_product) {
        this.featured_product = featured_product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(String offer_price) {
        this.offer_price = offer_price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCreated_user_id() {
        return created_user_id;
    }

    public void setCreated_user_id(String created_user_id) {
        this.created_user_id = created_user_id;
    }

    public String getIn_stock() {
        return in_stock;
    }

    public void setIn_stock(String in_stock) {
        this.in_stock = in_stock;
    }
}
