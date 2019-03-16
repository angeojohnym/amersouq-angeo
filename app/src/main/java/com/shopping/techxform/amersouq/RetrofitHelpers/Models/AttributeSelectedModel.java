package com.shopping.techxform.amersouq.RetrofitHelpers.Models;

public class AttributeSelectedModel {
    private String attribute_id;
    private String value;

    public AttributeSelectedModel(String attribute_id, String value) {
        this.attribute_id = attribute_id;
        this.value = value;
    }

    public String getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(String attribute_id) {
        this.attribute_id = attribute_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
