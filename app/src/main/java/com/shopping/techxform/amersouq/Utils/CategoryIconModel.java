package com.shopping.techxform.amersouq.Utils;

/**
 * Created by techxform on 05-Jan-19.
 */

public class CategoryIconModel {

    private String cat_id;
    private String cat_name;
    private int drawable_name;


    public CategoryIconModel(String cat_id, String cat_name, int drawable_name) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.drawable_name = drawable_name;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getDrawable_name() {
        return drawable_name;
    }

    public void setDrawable_name(int drawable_name) {
        this.drawable_name = drawable_name;
    }
}
