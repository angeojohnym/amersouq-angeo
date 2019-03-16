
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.OptionOutput;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OptionAddOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OptionAddOutput() {
    }

    /**
     * 
     * @param message
     * @param data
     * @param code
     */
    public OptionAddOutput(String code, String message, Data data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
