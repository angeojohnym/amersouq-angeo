
package com.shopping.techxform.amersouq.RetrofitHelpers;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.MyAdsOutput.AllAd;

public class MyAdsOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("all_ads")
    @Expose
    private List<AllAd> allAds = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MyAdsOutput() {
    }

    /**
     * 
     * @param message
     * @param code
     * @param allAds
     */
    public MyAdsOutput(String code, String message, List<AllAd> allAds) {
        super();
        this.code = code;
        this.message = message;
        this.allAds = allAds;
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

    public List<AllAd> getAllAds() {
        return allAds;
    }

    public void setAllAds(List<AllAd> allAds) {
        this.allAds = allAds;
    }

}
