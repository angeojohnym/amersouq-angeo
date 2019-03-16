
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.LanguageData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllLanguage {

    @SerializedName("language_id")
    @Expose
    private String languageId;
    @SerializedName("language")
    @Expose
    private String language;

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
