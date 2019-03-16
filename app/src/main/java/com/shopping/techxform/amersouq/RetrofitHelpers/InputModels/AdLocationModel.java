package com.shopping.techxform.amersouq.RetrofitHelpers.InputModels;

/**
 * Created by techxform on 10-Jan-19.
 */

public class AdLocationModel {

    private String latitude;
    private String longitude;
    private String address_location;
    private String ad_id;
    private String city;


    public AdLocationModel(String latitude, String longitude, String address_location, String ad_id, String city) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address_location = address_location;
        this.ad_id = ad_id;
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress_location() {
        return address_location;
    }

    public void setAddress_location(String address_location) {
        this.address_location = address_location;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
