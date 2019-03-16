
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.FixedPriceOutput;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fixed {

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
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("ad_visibility")
    @Expose
    private String adVisibility;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("address_info")
    @Expose
    private String addressInfo;
    @SerializedName("contact_info")
    @Expose
    private String contactInfo;
    @SerializedName("condition_id")
    @Expose
    private String conditionId;
    @SerializedName("locations_id")
    @Expose
    private String locationsId;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("breadth")
    @Expose
    private String breadth;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("shipping_package_id")
    @Expose
    private Object shippingPackageId;
    @SerializedName("main_cat")
    @Expose
    private String mainCat;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAdVisibility() {
        return adVisibility;
    }

    public void setAdVisibility(String adVisibility) {
        this.adVisibility = adVisibility;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public String getLocationsId() {
        return locationsId;
    }

    public void setLocationsId(String locationsId) {
        this.locationsId = locationsId;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getBreadth() {
        return breadth;
    }

    public void setBreadth(String breadth) {
        this.breadth = breadth;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public Object getShippingPackageId() {
        return shippingPackageId;
    }

    public void setShippingPackageId(Object shippingPackageId) {
        this.shippingPackageId = shippingPackageId;
    }

    public String getMainCat() {
        return mainCat;
    }

    public void setMainCat(String mainCat) {
        this.mainCat = mainCat;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
