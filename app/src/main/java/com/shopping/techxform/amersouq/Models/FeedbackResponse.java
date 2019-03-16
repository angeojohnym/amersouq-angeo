package com.shopping.techxform.amersouq.Models;

import java.util.ArrayList;

public class FeedbackResponse
{
    private ArrayList<Feedback> feedback;

    private String code;

    private String message;

    public ArrayList<Feedback> getFeedback ()
    {
        return feedback;
    }

    public void setFeedback (ArrayList<Feedback> feedback)
    {
        this.feedback = feedback;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
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