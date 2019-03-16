
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.All_Ads_Classified;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OptionAddOutput_Classified {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("all_ads")
    @Expose
    private List<AllAd_Classified> allAds = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OptionAddOutput_Classified() {
    }

    /**
     * 
     * @param message
     * @param code
     * @param allAds
     */
    public OptionAddOutput_Classified(String code, String message, List<AllAd_Classified> allAds) {
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

    public List<AllAd_Classified> getAllAds() {
        return allAds;
    }

    public void setAllAds(List<AllAd_Classified> allAds) {
        this.allAds = allAds;
    }

}
