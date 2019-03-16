
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AllCategory implements Serializable {

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("is_classified")
    @Expose
    private String isClassified;
    @SerializedName("is_auction")
    @Expose
    private String isAuction;
    @SerializedName("is_fixedprice")
    @Expose
    private String isFixedprice;
    @SerializedName("is_product")
    @Expose
    private String isProduct;
    @SerializedName("is_children")
    @Expose
    private Integer isChildren;

    public AllCategory(String categoryId, String categoryName, String isClassified, String isAuction, String isFixedprice, String isProduct, Integer isChildren) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.isClassified = isClassified;
        this.isAuction = isAuction;
        this.isFixedprice = isFixedprice;
        this.isProduct = isProduct;
        this.isChildren = isChildren;
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

    public String getIsClassified() {
        return isClassified;
    }

    public void setIsClassified(String isClassified) {
        this.isClassified = isClassified;
    }

    public String getIsAuction() {
        return isAuction;
    }

    public void setIsAuction(String isAuction) {
        this.isAuction = isAuction;
    }

    public String getIsFixedprice() {
        return isFixedprice;
    }

    public void setIsFixedprice(String isFixedprice) {
        this.isFixedprice = isFixedprice;
    }

    public String getIsProduct() {
        return isProduct;
    }

    public void setIsProduct(String isProduct) {
        this.isProduct = isProduct;
    }

    public Integer getIsChildren() {
        return isChildren;
    }

    public void setIsChildren(Integer isChildren) {
        this.isChildren = isChildren;
    }

}
