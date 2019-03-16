package com.shopping.techxform.amersouq.fragments.amer_souq_collections;

/**
 * Created by techxform on 04-Dec-18.
 */

public class CategoryModel {

    private int img_drawable;
    private String title_category;
    private String cat_id;

    public CategoryModel(int img_drawable, String title_category, String cat_id) {
        this.img_drawable = img_drawable;
        this.title_category = title_category;
        this.cat_id = cat_id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public int getImg_drawable() {
        return img_drawable;
    }

    public void setImg_drawable(int img_drawable) {
        this.img_drawable = img_drawable;
    }

    public String getTitle_category() {
        return title_category;
    }

    public void setTitle_category(String title_category) {
        this.title_category = title_category;
    }
}
