
package com.shopping.techxform.amersouq.RetrofitHelpers.InputModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdCoordinatesOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ad_location_id")
    @Expose
    private Integer adLocationId;

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

    public Integer getAdLocationId() {
        return adLocationId;
    }

    public void setAdLocationId(Integer adLocationId) {
        this.adLocationId = adLocationId;
    }

}
