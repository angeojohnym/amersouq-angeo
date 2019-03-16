
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.OptionOutput;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("option_id")
    @Expose
    private Integer optionId;
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
     * @param optionId
     * @param date
     */
    public Data(Integer optionId, String date) {
        super();
        this.optionId = optionId;
        this.date = date;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
