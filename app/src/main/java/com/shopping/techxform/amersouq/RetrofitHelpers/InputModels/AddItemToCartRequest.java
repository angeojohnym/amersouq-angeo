package com.shopping.techxform.amersouq.RetrofitHelpers.InputModels;

import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.Product;

import java.util.ArrayList;

public class AddItemToCartRequest
{
    private ArrayList<Product> product;

    private String owner_id;

    private String status;


    public ArrayList<Product> getProduct ()
    {
        return product;
    }

    public void setProduct (ArrayList<Product> product)
    {
        this.product = product;
    }

    public String getOwner_id ()
    {
        return owner_id;
    }

    public void setOwner_id (String owner_id)
    {
        this.owner_id = owner_id;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [product = "+product+", owner_id = "+owner_id+", status = "+status+"]";
    }
}