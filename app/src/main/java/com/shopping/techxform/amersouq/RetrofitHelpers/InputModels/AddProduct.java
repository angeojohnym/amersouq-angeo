package com.shopping.techxform.amersouq.RetrofitHelpers.InputModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddProduct {
    @SerializedName("product_name")
    @Expose
    private String title;
    @SerializedName("short_desc")
    @Expose
    private String short_description;
    @SerializedName("full_desc")
    @Expose
    private String full_description;
    @SerializedName("boost_status")
    @Expose
    private int boost_status;
    @SerializedName("featured_product")
    @Expose
    private int featured_product;
    @SerializedName("available_to_guest")
    @Expose
    private int available_to_guest;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("offer_price")
    @Expose
    private float offer_price;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("package_type")
    @Expose
    private String package_type;
    @SerializedName("length")
    @Expose
    private float length;
    @SerializedName("latitude")
    @Expose
    private float latitude;
    @SerializedName("longitude")
    @Expose
    private float longitude;
    @SerializedName("cityname")
    @Expose
    private String cityname;
    @SerializedName("category_id")
    @Expose
    private int category;
    @SerializedName("created_user_id")
    @Expose
    private int created_user_id;



    public AddProduct(String title, String short_description, String full_description,
                      int boost_status, int featured_product, int available_to_guest,
                      String language, float price, float offer_price, String contact,
                      String address, String condition, String package_type, float length,
                      float latitude, float longitude, String cityname, int category) {
        this.title = title;
        this.short_description = short_description;
        this.full_description = full_description;
        this.boost_status = boost_status;
        this.featured_product = featured_product;
        this.available_to_guest = available_to_guest;
        this.language = language;
        this.price = price;
        this.offer_price = offer_price;
        this.contact = contact;
        this.address = address;
        this.condition = condition;
        this.package_type = package_type;
        this.length = length;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityname = cityname;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getBoost_status() {
        return boost_status;
    }

    public void setBoost_status(int boost_status) {
        this.boost_status = boost_status;
    }

    public int getFeatured_product() {
        return featured_product;
    }

    public void setFeatured_product(int featured_product) {
        this.featured_product = featured_product;
    }

    public int getAvailable_to_guest() {
        return available_to_guest;
    }

    public void setAvailable_to_guest(int available_to_guest) {
        this.available_to_guest = available_to_guest;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(float offer_price) {
        this.offer_price = offer_price;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPackage_type() {
        return package_type;
    }

    public void setPackage_type(String package_type) {
        this.package_type = package_type;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCreated_user_id() {
        return created_user_id;
    }

    public void setCreated_user_id(int created_user_id) {
        this.created_user_id = created_user_id;
    }
}
