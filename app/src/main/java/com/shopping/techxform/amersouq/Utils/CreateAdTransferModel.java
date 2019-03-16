package com.shopping.techxform.amersouq.Utils;

import java.io.Serializable;

/**
 * Created by techxform on 03-Jan-19.
 */

public class CreateAdTransferModel implements Serializable {

    private String cat_id;
    private String language;
    private String ad_title;
    private String ad_short_desc;
    private String ad_desc;
    private String main_cat;
    private String contactinfo;
    private String best_Price;
    private String reserved_Price;
    private String start_From;
    private String category_Type;
    private String ad_id;
    private String offerPrice;
    private String address;
    private String classifiedType;
    private String price;
    private int visible_state;





    public CreateAdTransferModel(String ad_id, String cat_id, String language, String ad_title, String ad_short_desc, String ad_desc, String main_cat,
                                 int visible_state, String contactinfo, String best_Price, String reserved_Price, String start_From, String category_Type, String offerPrice,
                                 String address, String price, String classifiedType) {
        this.cat_id = cat_id;
        this.language = language;
        this.ad_title = ad_title;
        this.ad_short_desc = ad_short_desc;
        this.ad_desc = ad_desc;
        this.main_cat = main_cat;
        this.visible_state = visible_state;
        this.contactinfo = contactinfo;
        this.best_Price = best_Price;
        this.reserved_Price = reserved_Price;
        this.start_From = start_From;
        this.category_Type = category_Type;
        this.ad_id = ad_id;
        this.offerPrice = offerPrice;
        this.address = address;
        this.price = price;
        this.classifiedType = classifiedType;
    }


    public String getClassifiedType() {
        return classifiedType;
    }

    public void setClassifiedType(String classifiedType) {
        classifiedType = classifiedType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        price = price;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getCategory_Type() {
        return category_Type;
    }

    public void setCategory_Type(String category_Type) {
        this.category_Type = category_Type;
    }

    public String getContactinfo() {
        return contactinfo;
    }

    public void setContactinfo(String contactinfo) {
        this.contactinfo = contactinfo;
    }

    public String getBest_Price() {
        return best_Price;
    }

    public void setBest_Price(String best_Price) {
        this.best_Price = best_Price;
    }

    public String getReserved_Price() {
        return reserved_Price;
    }

    public void setReserved_Price(String reserved_Price) {
        this.reserved_Price = reserved_Price;
    }

    public String getStart_From() {
        return start_From;
    }

    public void setStart_From(String start_From) {
        this.start_From = start_From;
    }

    public int getVisible_state() {
        return visible_state;
    }

    public void setVisible_state(int visible_state) {
        this.visible_state = visible_state;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAd_title() {
        return ad_title;
    }

    public void setAd_title(String ad_title) {
        this.ad_title = ad_title;
    }

    public String getAd_short_desc() {
        return ad_short_desc;
    }

    public void setAd_short_desc(String ad_short_desc) {
        this.ad_short_desc = ad_short_desc;
    }

    public String getAd_desc() {
        return ad_desc;
    }

    public void setAd_desc(String ad_desc) {
        this.ad_desc = ad_desc;
    }

    public String getMain_cat() {
        return main_cat;
    }

    public void setMain_cat(String main_cat) {
        this.main_cat = main_cat;
    }
}
