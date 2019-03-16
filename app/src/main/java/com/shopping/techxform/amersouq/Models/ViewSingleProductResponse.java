package com.shopping.techxform.amersouq.Models;

import java.util.ArrayList;

public class ViewSingleProductResponse
{
    private String code;

    private ArrayList<Single_product> single_product;

    private String message;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public ArrayList<Single_product> getSingle_product ()
    {
        return single_product;
    }

    public void setSingle_product (ArrayList<Single_product> single_product)
    {
        this.single_product = single_product;
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
        return "ClassPojo [code = "+code+", single_product = "+single_product+", message = "+message+"]";
    }
}
			
			