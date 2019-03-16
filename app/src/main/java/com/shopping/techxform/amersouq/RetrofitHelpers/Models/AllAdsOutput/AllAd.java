
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.AllAdsOutput;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllAd {

    @SerializedName("ad_id")
    @Expose
    private String adId;
    @SerializedName("User")
    @Expose
    private String user;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("ad_name")
    @Expose
    private Object adName;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("ad_type")
    @Expose
    private String adType;
    @SerializedName("added_on")
    @Expose
    private String addedOn;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("offer_price")
    @Expose
    private String offerPrice;
    @SerializedName("images")
    @Expose
    private List<String> images = null;

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getAdName() {
        return adName;
    }

    public void setAdName(Object adName) {
        this.adName = adName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
