package com.shopping.techxform.amersouq.RetrofitHelpers.Models;

public class Cart
{
    private String cart_id;

    private String date_submited;

    private String date_created;

    private String owner_id;

    private String status;

    public String getCart_id ()
    {
        return cart_id;
    }

    public void setCart_id (String cart_id)
    {
        this.cart_id = cart_id;
    }

    public String getDate_submited ()
    {
        return date_submited;
    }

    public void setDate_submited (String date_submited)
    {
        this.date_submited = date_submited;
    }

    public String getDate_created ()
    {
        return date_created;
    }

    public void setDate_created (String date_created)
    {
        this.date_created = date_created;
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
        return "ClassPojo [cart_id = "+cart_id+", date_submited = "+date_submited+", date_created = "+date_created+", owner_id = "+owner_id+", status = "+status+"]";
    }
}
			
	