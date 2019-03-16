
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.LocationsData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationsOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("all_locations")
    @Expose
    private List<AllLocation> allLocations = null;

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

    public List<AllLocation> getAllLocations() {
        return allLocations;
    }

    public void setAllLocations(List<AllLocation> allLocations) {
        this.allLocations = allLocations;
    }

}
