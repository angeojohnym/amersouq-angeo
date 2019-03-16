
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.All_Ads_Auction;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllAd_Auc {

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
    private String adName;
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
    @SerializedName("ad_type_id")
    @Expose
    private String adTypeId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AllAd_Auc() {
    }

    /**
     * 
     * @param price
     * @param shortDescription
     * @param adId
     * @param description
     * @param offerPrice
     * @param userId
     * @param images
     * @param addedOn
     * @param adType
     * @param user
     * @param adName
     * @param adTypeId
     */
    public AllAd_Auc(String adId, String user, String userId, String adName, String shortDescription, String description, String adType, String addedOn, String price, String offerPrice, List<String> images, String adTypeId) {
        super();
        this.adId = adId;
        this.user = user;
        this.userId = userId;
        this.adName = adName;
        this.shortDescription = shortDescription;
        this.description = description;
        this.adType = adType;
        this.addedOn = addedOn;
        this.price = price;
        this.offerPrice = offerPrice;
        this.images = images;
        this.adTypeId = adTypeId;
    }

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

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
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

    public String getAdTypeId() {
        return adTypeId;
    }

    public void setAdTypeId(String adTypeId) {
        this.adTypeId = adTypeId;
    }

}
