
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.WishOutput;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WishOutputList {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("wish_ads")
    @Expose
    private List<WishAd> wishAds = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WishOutputList() {
    }

    /**
     * 
     * @param message
     * @param wishAds
     * @param code
     */
    public WishOutputList(String code, String message, List<WishAd> wishAds) {
        super();
        this.code = code;
        this.message = message;
        this.wishAds = wishAds;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<WishAd> getWishAds() {
        return wishAds;
    }

    public void setWishAds(List<WishAd> wishAds) {
        this.wishAds = wishAds;
    }

}
