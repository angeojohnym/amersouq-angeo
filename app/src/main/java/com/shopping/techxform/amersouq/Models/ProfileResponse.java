package com.shopping.techxform.amersouq.Models;

import java.util.ArrayList;

public class ProfileResponse
{
    private String code;

    private ArrayList<User_profile> user_profile;

    private String message;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public ArrayList<User_profile> getUser_profile ()
    {
        return user_profile;
    }

    public void setUser_profile (ArrayList<User_profile> user_profile)
    {
        this.user_profile = user_profile;
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