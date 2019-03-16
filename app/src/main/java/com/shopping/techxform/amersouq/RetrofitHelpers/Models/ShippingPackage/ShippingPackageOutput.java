
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.ShippingPackage;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShippingPackageOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("all_shippingpackage")
    @Expose
    private List<AllShippingpackage> allShippingpackage = null;

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

    public List<AllShippingpackage> getAllShippingpackage() {
        return allShippingpackage;
    }

    public void setAllShippingpackage(List<AllShippingpackage> allShippingpackage) {
        this.allShippingpackage = allShippingpackage;
    }

}
