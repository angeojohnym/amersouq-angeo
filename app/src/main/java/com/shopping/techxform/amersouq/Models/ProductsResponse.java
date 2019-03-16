package com.shopping.techxform.amersouq.Models;

import java.util.ArrayList;

public class ProductsResponse
{
    private String code;

    private ArrayList<All_products> all_products;

    private String message;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public ArrayList<All_products> getAll_products ()
    {
        return all_products;
    }

    public void setAll_products (ArrayList<All_products> all_products)
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

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", all_products = "+all_products+", message = "+message+"]";
    }
}