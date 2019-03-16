
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.ClassifiedData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewClassifiedOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("classified")
    @Expose
    private Classified classified;

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

    public Classified getClassified() {
        return classified;
    }

    public void setClassified(Classified classified) {
        this.classified = classified;
    }

}
