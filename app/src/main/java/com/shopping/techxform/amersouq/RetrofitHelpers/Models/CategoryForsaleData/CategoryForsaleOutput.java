
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryForsaleData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryForsaleOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("forsale_category")
    @Expose
    private List<ForsaleCategory> forsaleCategory = null;

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

    public List<ForsaleCategory> getForsaleCategory() {
        return forsaleCategory;
    }

    public void setForsaleCategory(List<ForsaleCategory> forsaleCategory) {
        this.forsaleCategory = forsaleCategory;
    }

}
