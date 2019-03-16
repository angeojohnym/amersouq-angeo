
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.ClassifiedTypeData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassifiedTypeOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("all_classified_types")
    @Expose
    private List<AllClassifiedType> allClassifiedTypes = null;

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

    public List<AllClassifiedType> getAllClassifiedTypes() {
        return allClassifiedTypes;
    }

    public void setAllClassifiedTypes(List<AllClassifiedType> allClassifiedTypes) {
        this.allClassifiedTypes = allClassifiedTypes;
    }

}
