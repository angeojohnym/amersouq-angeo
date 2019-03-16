package com.shopping.techxform.amersouq.Models;

import java.io.Serializable;

public class CheckoutItem implements Serializable {

    private String title;
    private String ad_id;
    private double unit_price;
    private int count;
    private String desc;
    private double total_price;

    public CheckoutItem(String title, String ad_id, double unit_price, int count, double total_price,String desc) {
        this.title = title;
        this.ad_id = ad_id;
        this.unit_price = unit_price;
        this.count = count;
        this.total_price = total_price;
        this.desc = desc;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
