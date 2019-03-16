
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.ConditionsData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllCondition {

    @SerializedName("condition_id")
    @Expose
    private String conditionId;
    @SerializedName("condition_name")
    @Expose
    private String conditionName;

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

}
