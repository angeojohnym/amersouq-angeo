
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.ProductImageUpload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageUploadOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ad_id")
    @Expose
    private String adId;

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

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

}
