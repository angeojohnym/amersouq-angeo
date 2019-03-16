
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.AllAdsOutput;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewAllAdsOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("all_ads")
    @Expose
    private List<AllAd> allAds = null;

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

    public List<AllAd> getAllAds() {
        return allAds;
    }

    public void setAllAds(List<AllAd> allAds) {
        this.allAds = allAds;
    }

}
