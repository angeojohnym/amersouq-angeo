package com.shopping.techxform.amersouq.Models;

import java.util.ArrayList;

public class ProfileUpdateResponse
{
    private String code;

    private ArrayList<Data> data;

    private String message;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public ArrayList<Data> getData ()
    {
        return data;
    }

    public void setData (ArrayList<Data> data)
    {
        this.data = data;
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