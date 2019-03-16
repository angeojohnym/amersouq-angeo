
package com.shopping.techxform.amersouq.RetrofitHelpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SampleResult {

    @SerializedName("User")
    @Expose
    private Integer user;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SampleResult() {
    }

    /**
     * 
     * @param user
     */
    public SampleResult(Integer user) {
        super();
        this.user = user;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

}
