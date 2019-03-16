
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoriesOutput {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("all_categories")
    @Expose
    private List<AllCategory> allCategories = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<AllCategory> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(List<AllCategory> allCategories) {
        this.allCategories = allCategories;
    }

}
