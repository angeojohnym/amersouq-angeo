package com.shopping.techxform.amersouq.Models;

import java.util.ArrayList;

public class AlertResponse
{
    private String code;

    private ArrayList<Alert> alert;

    private String message;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public ArrayList<Alert> getAlert ()
    {
        return alert;
    }

    public void setAlert (ArrayList<Alert> alert)
    {
        this.alert = alert;
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