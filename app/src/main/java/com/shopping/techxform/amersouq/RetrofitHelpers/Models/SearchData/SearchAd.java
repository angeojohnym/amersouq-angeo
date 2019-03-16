
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.SearchData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchAd implements Serializable {

    @SerializedName("ad_id")
    @Expose
    private String adId;
    @SerializedName("ad_name")
    @Expose
    private String adName;
    @SerializedName("ad_type_id")
    @Expose
    private String adTypeId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SearchAd() {
    }

    /**
     * 
     * @param adId
     * @param adName
     * @param adTypeId
     */
    public SearchAd(String adId, String adName, String adTypeId) {
        super();
        this.adId = adId;
        this.adName = adName;
        this.adTypeId = adTypeId;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdTypeId() {
        return adTypeId;
    }

    public void setAdTypeId(String adTypeId) {
        this.adTypeId = adTypeId;
    }

}
