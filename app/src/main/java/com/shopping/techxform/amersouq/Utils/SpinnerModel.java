package com.shopping.techxform.amersouq.Utils;

/**
 * Created by techxform on 31-Dec-18.
 */

public class SpinnerModel {


    private String id;
    private String name;

    public SpinnerModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
