
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.SearchData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchOuputModel {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("search_ads")
    @Expose
    private List<SearchAd> searchAds = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SearchOuputModel() {
    }

    /**
     * 
     * @param searchAds
     * @param message
     * @param code
     */
    public SearchOuputModel(String code, String message, List<SearchAd> searchAds) {
        super();
        this.code = code;
        this.message = message;
        this.searchAds = searchAds;
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

    public List<SearchAd> getSearchAds() {
        return searchAds;
    }

    public void setSearchAds(List<SearchAd> searchAds) {
        this.searchAds = searchAds;
    }

}
