package com.shopping.techxform.amersouq.Models;

public class Feedback
{
    private String user_name;

    private String userimage;

    private String title;

    private String body;

    private String product_name;

    private String feed_id;

    public String getUser_name ()
    {
        return user_name;
    }

    public void setUser_name (String user_name)
    {
        this.user_name = user_name;
    }

    public String getUserimage ()
    {
        return userimage;
    }

    public void setUserimage (String userimage)
    {
        this.userimage = userimage;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getBody ()
    {
        return body;
    }

    public void setBody (String body)
    {
        this.body = body;
    }

    public String getProduct_name ()
    {
        return product_name;
    }

    public void setProduct_name (String product_name)
    {
        this.product_name = product_name;
    }

    public String getFeed_id ()
    {
        return feed_id;
    }

    public void setFeed_id (String feed_id)
    {
        this.feed_id = feed_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [user_name = "+user_name+", userimage = "+userimage+", title = "+title+", body = "+body+", product_name = "+product_name+", feed_id = "+feed_id+"]";
    }
}
