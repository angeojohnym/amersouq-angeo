package com.shopping.techxform.amersouq.RetrofitHelpers.InputModels;

public class Product
{
    private String quantity;

    private String product_id;

    public String getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (String quantity)
    {
        this.quantity = quantity;
    }

    public String getProduct_id ()
    {
        return product_id;
    }

    public void setProduct_id (String product_id)
    {
        this.product_id = product_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [quantity = "+quantity+", product_id = "+product_id+"]";
    }
}
			
			