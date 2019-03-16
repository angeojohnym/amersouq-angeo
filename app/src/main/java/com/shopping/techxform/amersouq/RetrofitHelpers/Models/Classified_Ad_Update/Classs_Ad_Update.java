
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.Classified_Ad_Update;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Classs_Ad_Update {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ad_id")
    @Expose
    private String ad_id;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Classs_Ad_Update() {
    }

    /**
     * 
     * @param message
     * @param code
     */
    public Classs_Ad_Update(String code, String message) {
        super();
        this.code = code;
        this.message = message;
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

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }
}
