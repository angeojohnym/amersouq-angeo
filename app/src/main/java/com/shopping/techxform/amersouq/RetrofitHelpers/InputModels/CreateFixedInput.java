
package com.shopping.techxform.amersouq.RetrofitHelpers.InputModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateFixedInput {

    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("ad_visibility")
    @Expose
    private Integer adVisibility;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("offer_price")
    @Expose
    private String offerPrice;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("created_user_id")
    @Expose
    private Integer createdUserId;
    @SerializedName("address_info")
    @Expose
    private String addressInfo;
    @SerializedName("contact_info")
    @Expose
    private String contactInfo;
    @SerializedName("condition_id")
    @Expose
    private Integer conditionId;
    @SerializedName("locations_id")
    @Expose
    private Integer locationsId;
    @SerializedName("shipping_package_id")
    @Expose
    private Integer shippingPackageId;
    @SerializedName("main_cat")
    @Expose
    private String mainCat;
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

    /**
     * No args constructor for use in serialization
     * 
     */

    /**
     * 
     * @param breadth
     * @param weight
     * @param width
     * @param shippingPackageId
     * @param categoryId
     * @param adVisibility
     * @param locationsId
     * @param title
     * @param createdUserId
     * @param conditionId
     * @param price
     * @param contactInfo
     * @param shortDescription
     * @param description
     * @param offerPrice
     * @param addressInfo
     * @param length
     * @param mainCat
     * @param language
     */
    public CreateFixedInput(String language, String title, String shortDescription, String description, Integer adVisibility, String price, String offerPrice, Integer categoryId, Integer createdUserId, String addressInfo, String contactInfo, Integer conditionId, Integer locationsId, Integer shippingPackageId, String mainCat, String length, String breadth, String weight, String width) {
        super();
        this.language = language;
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.adVisibility = adVisibility;
        this.price = price;
        this.offerPrice = offerPrice;
        this.categoryId = categoryId;
        this.createdUserId = createdUserId;
        this.addressInfo = addressInfo;
        this.contactInfo = contactInfo;
        this.conditionId = conditionId;
        this.locationsId = locationsId;
        this.shippingPackageId = shippingPackageId;
        this.mainCat = mainCat;
        this.length = length;
        this.breadth = breadth;
        this.weight = weight;
        this.width = width;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getAdVisibility() {
        return adVisibility;
    }

    public void setAdVisibility(Integer adVisibility) {
        this.adVisibility = adVisibility;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Integer createdUserId) {
        this.createdUserId = createdUserId;
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

    public Integer getConditionId() {
        return conditionId;
    }

    public void setConditionId(Integer conditionId) {
        this.conditionId = conditionId;
    }

    public Integer getLocationsId() {
        return locationsId;
    }

    public void setLocationsId(Integer locationsId) {
        this.locationsId = locationsId;
    }

    public Integer getShippingPackageId() {
        return shippingPackageId;
    }

    public void setShippingPackageId(Integer shippingPackageId) {
        this.shippingPackageId = shippingPackageId;
    }

    public String getMainCat() {
        return mainCat;
    }

    public void setMainCat(String mainCat) {
        this.mainCat = mainCat;
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

}
