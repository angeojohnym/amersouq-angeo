
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.LanguageData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LanguagesOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("all_languages")
    @Expose
    private List<AllLanguage> allLanguages = null;

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

    public List<AllLanguage> getAllLanguages() {
        return allLanguages;
    }

    public void setAllLanguages(List<AllLanguage> allLanguages) {
        this.allLanguages = allLanguages;
    }

}
