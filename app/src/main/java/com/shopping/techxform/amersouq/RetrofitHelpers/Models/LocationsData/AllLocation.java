
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.LocationsData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllLocation {

    @SerializedName("location_id")
    @Expose
    private String locationId;
    @SerializedName("location_name")
    @Expose
    private String locationName;
    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("country_id")
    @Expose
    private String countryId;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String categoryName) {
        this.locationName = locationName;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

}
