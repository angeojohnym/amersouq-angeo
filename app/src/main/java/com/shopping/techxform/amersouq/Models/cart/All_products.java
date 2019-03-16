package com.shopping.techxform.amersouq.Models.cart;

import com.google.gson.annotations.SerializedName;

public class All_products
{
    @SerializedName("seller")
    private String seller;

    @SerializedName("image")
    private String image;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("price")
    private String price;

    @SerializedName("title")
    private String title;

    @SerializedName("offer_price")
    private String offer_price;

    public String getSeller ()
    {
        return seller;
    }

    public void setSeller (String seller)
    {
        this.seller = seller;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (String quantity)
    {
        this.quantity = quantity;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getOffer_price ()
    {
        return offer_price;
    }

    public void setOffer_price (String offer_price)
    {
        this.offer_price = offer_price;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [seller = "+seller+", image = "+image+", quantity = "+quantity+", price = "+price+", title = "+title+", offer_price = "+offer_price+"]";
    }
}