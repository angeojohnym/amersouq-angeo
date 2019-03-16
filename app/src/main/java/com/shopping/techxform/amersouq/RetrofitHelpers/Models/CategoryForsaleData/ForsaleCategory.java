
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryForsaleData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForsaleCategory {


    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("added_on")
    @Expose
    private String addedOn;
    @SerializedName("updated_on")
    @Expose
    private String updatedOn;
    @SerializedName("is_auction")
    @Expose
    private String isAuction;
    @SerializedName("is_classified")
    @Expose
    private String isClassified;
    @SerializedName("is_product")
    @Expose
    private String isProduct;
    @SerializedName("is_fixed_price")
    @Expose
    private String isFixedPrice;
    @SerializedName("image")
    @Expose
    private String image;


    public ForsaleCategory(String categoryId, String categoryName, String slug, String addedOn, String updatedOn, String isAuction, String isClassified, String isProduct, String isFixedPrice, String image) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.slug = slug;
        this.addedOn = addedOn;
        this.updatedOn = updatedOn;
        this.isAuction = isAuction;
        this.isClassified = isClassified;
        this.isProduct = isProduct;
        this.isFixedPrice = isFixedPrice;
        this.image = image;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getIsAuction() {
        return isAuction;
    }

    public void setIsAuction(String isAuction) {
        this.isAuction = isAuction;
    }

    public String getIsClassified() {
        return isClassified;
    }

    public void setIsClassified(String isClassified) {
        this.isClassified = isClassified;
    }

    public String getIsProduct() {
        return isProduct;
    }

    public void setIsProduct(String isProduct) {
        this.isProduct = isProduct;
    }

    public String getIsFixedPrice() {
        return isFixedPrice;
    }

    public void setIsFixedPrice(String isFixedPrice) {
        this.isFixedPrice = isFixedPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
