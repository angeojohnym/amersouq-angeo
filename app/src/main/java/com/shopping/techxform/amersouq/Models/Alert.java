package com.shopping.techxform.amersouq.Models;

public class Alert
{
    private String image;

    private String id;

    private String message;

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
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
        return "ClassPojo [image = "+image+", id = "+id+", message = "+message+"]";
    }
}
