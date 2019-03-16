
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.packages_module;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("package_id")
    @Expose
    private String packageId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ads")
    @Expose
    private String ads;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("added_on")
    @Expose
    private String addedOn;
    @SerializedName("featured_ads")
    @Expose
    private String featuredAds;
    @SerializedName("valididty")
    @Expose
    private Object valididty;
    @SerializedName("bump_up_ads")
    @Expose
    private String bumpUpAds;
    @SerializedName("can_create_auction")
    @Expose
    private String canCreateAuction;
    @SerializedName("can_create_vendor_profile")
    @Expose
    private String canCreateVendorProfile;
    @SerializedName("hompe_page_live")
    @Expose
    private String hompePageLive;
    @SerializedName("can_create_classified_ad")
    @Expose
    private String canCreateClassifiedAd;
    @SerializedName("can_create_fixed_price_ad")
    @Expose
    private String canCreateFixedPriceAd;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("is_monthly")
    @Expose
    private String isMonthly;
    @SerializedName("is_yearly")
    @Expose
    private String isYearly;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param bumpUpAds
     * @param isYearly
     * @param canCreateClassifiedAd
     * @param canCreateFixedPriceAd
     * @param featuredAds
     * @param packageId
     * @param addedOn
     * @param isMonthly
     * @param discount
     * @param valididty
     * @param hompePageLive
     * @param price
     * @param canCreateAuction
     * @param name
     * @param ads
     * @param canCreateVendorProfile
     */
    public Datum(String packageId, String name, String ads, String price, String addedOn, String featuredAds, Object valididty, String bumpUpAds, String canCreateAuction, String canCreateVendorProfile, String hompePageLive, String canCreateClassifiedAd, String canCreateFixedPriceAd, String discount, String isMonthly, String isYearly) {
        super();
        this.packageId = packageId;
        this.name = name;
        this.ads = ads;
        this.price = price;
        this.addedOn = addedOn;
        this.featuredAds = featuredAds;
        this.valididty = valididty;
        this.bumpUpAds = bumpUpAds;
        this.canCreateAuction = canCreateAuction;
        this.canCreateVendorProfile = canCreateVendorProfile;
        this.hompePageLive = hompePageLive;
        this.canCreateClassifiedAd = canCreateClassifiedAd;
        this.canCreateFixedPriceAd = canCreateFixedPriceAd;
        this.discount = discount;
        this.isMonthly = isMonthly;
        this.isYearly = isYearly;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAds() {
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getFeaturedAds() {
        return featuredAds;
    }

    public void setFeaturedAds(String featuredAds) {
        this.featuredAds = featuredAds;
    }

    public Object getValididty() {
        return valididty;
    }

    public void setValididty(Object valididty) {
        this.valididty = valididty;
    }

    public String getBumpUpAds() {
        return bumpUpAds;
    }

    public void setBumpUpAds(String bumpUpAds) {
        this.bumpUpAds = bumpUpAds;
    }

    public String getCanCreateAuction() {
        return canCreateAuction;
    }

    public void setCanCreateAuction(String canCreateAuction) {
        this.canCreateAuction = canCreateAuction;
    }

    public String getCanCreateVendorProfile() {
        return canCreateVendorProfile;
    }

    public void setCanCreateVendorProfile(String canCreateVendorProfile) {
        this.canCreateVendorProfile = canCreateVendorProfile;
    }

    public String getHompePageLive() {
        return hompePageLive;
    }

    public void setHompePageLive(String hompePageLive) {
        this.hompePageLive = hompePageLive;
    }

    public String getCanCreateClassifiedAd() {
        return canCreateClassifiedAd;
    }

    public void setCanCreateClassifiedAd(String canCreateClassifiedAd) {
        this.canCreateClassifiedAd = canCreateClassifiedAd;
    }

    public String getCanCreateFixedPriceAd() {
        return canCreateFixedPriceAd;
    }

    public void setCanCreateFixedPriceAd(String canCreateFixedPriceAd) {
        this.canCreateFixedPriceAd = canCreateFixedPriceAd;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getIsMonthly() {
        return isMonthly;
    }

    public void setIsMonthly(String isMonthly) {
        this.isMonthly = isMonthly;
    }

    public String getIsYearly() {
        return isYearly;
    }

    public void setIsYearly(String isYearly) {
        this.isYearly = isYearly;
    }


}
