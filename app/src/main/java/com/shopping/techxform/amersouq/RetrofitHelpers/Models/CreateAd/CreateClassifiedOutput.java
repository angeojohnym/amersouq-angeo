
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.CreateAd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateClassifiedOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ad_id")
    @Expose
    private Integer adId;

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

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

}
