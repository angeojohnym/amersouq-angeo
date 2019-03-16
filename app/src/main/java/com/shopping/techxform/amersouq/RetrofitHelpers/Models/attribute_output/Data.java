
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("attr_id")
    @Expose
    private Integer attrId;
    @SerializedName("date")
    @Expose
    private String date;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param attrId
     * @param date
     */
    public Data(Integer attrId, String date) {
        super();
        this.attrId = attrId;
        this.date = date;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
