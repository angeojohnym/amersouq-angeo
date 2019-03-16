
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.ClassifiedTypeData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllClassifiedType {

    @SerializedName("adtype_id")
    @Expose
    private String adtypeId;
    @SerializedName("adtype")
    @Expose
    private String adtype;

    public String getAdtypeId() {
        return adtypeId;
    }

    public void setAdtypeId(String adtypeId) {
        this.adtypeId = adtypeId;
    }

    public String getAdtype() {
        return adtype;
    }

    public void setAdtype(String adtype) {
        this.adtype = adtype;
    }

}
