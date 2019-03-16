package com.shopping.techxform.amersouq.RetrofitHelpers.Models;

import java.util.ArrayList;

public class AddToCartResponse
{
    private String code;

    private String message;

    private ArrayList<Cart> cart;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public ArrayList<Cart> getCart ()
    {
        return cart;
    }

    public void setCart (ArrayList<Cart> cart)
    {
        this.cart = cart;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", message = "+message+", cart = "+cart+"]";
    }
}
			
			