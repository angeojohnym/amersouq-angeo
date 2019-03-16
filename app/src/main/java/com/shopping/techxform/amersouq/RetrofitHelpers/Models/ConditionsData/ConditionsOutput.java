
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.ConditionsData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConditionsOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("all_conditions")
    @Expose
    private List<AllCondition> allConditions = null;

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

    public List<AllCondition> getAllConditions() {
        return allConditions;
    }

    public void setAllConditions(List<AllCondition> allConditions) {
        this.allConditions = allConditions;
    }

}
