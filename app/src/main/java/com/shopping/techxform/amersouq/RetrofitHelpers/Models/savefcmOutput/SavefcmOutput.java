
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.savefcmOutput;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SavefcmOutput {

    @SerializedName("status")
    @Expose
    private Status status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SavefcmOutput() {
    }

    /**
     * 
     * @param status
     */
    public SavefcmOutput(Status status) {
        super();
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
