
package com.shopping.techxform.amersouq.RetrofitHelpers.InputModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OptionAddInput {

    @SerializedName("attr_id")
    @Expose
    private String attrId;
    @SerializedName("option")
    @Expose
    private String option;
    @SerializedName("user_id")
    @Expose
    private String userId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OptionAddInput() {
    }

    /**
     * 
     * @param attrId
     * @param userId
     * @param option
     */
    public OptionAddInput(String attrId, String option, String userId) {
        super();
        this.attrId = attrId;
        this.option = option;
        this.userId = userId;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
