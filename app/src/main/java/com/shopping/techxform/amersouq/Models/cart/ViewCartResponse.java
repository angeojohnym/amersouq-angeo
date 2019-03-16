package com.shopping.techxform.amersouq.Models.cart;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ViewCartResponse
{
    @SerializedName("code")
    private String code;

    @SerializedName("all_products")
    private ArrayList<CartSingleModel> all_products;

    @SerializedName("message")
    private String message;

    @SerializedName("cart_id")
    private String cart_id;

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public ArrayList<CartSingleModel> getAll_products ()
    {
        return all_products;
    }

    public void setAll_products (ArrayList<CartSingleModel> all_products)
    {
        this.all_products = all_products;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

}
			
			