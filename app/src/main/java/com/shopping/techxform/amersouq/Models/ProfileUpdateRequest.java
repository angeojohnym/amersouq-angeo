package com.shopping.techxform.amersouq.Models;

public class ProfileUpdateRequest
{
    private String country_code;

    private String address;

    private String city;

    private String last_name;

    private String state;

    private String postal_code;

    private String userid;

    private String first_name;

    private String email;

    private String contact_number;

    public String getCountry_code ()
    {
        return country_code;
    }

    public void setCountry_code (String country_code)
    {
        this.country_code = country_code;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getPostal_code ()
    {
        return postal_code;
    }

    public void setPostal_code (String postal_code)
    {
        this.postal_code = postal_code;
    }

    public String getUserid ()
    {
        return userid;
    }

    public void setUserid (String userid)
    {
        this.userid = userid;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getContact_number ()
    {
        return contact_number;
    }

    public void setContact_number (String contact_number)
    {
        this.contact_number = contact_number;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [country_code = "+country_code+", address = "+address+", city = "+city+", last_name = "+last_name+", state = "+state+", postal_code = "+postal_code+", userid = "+userid+", first_name = "+first_name+", email = "+email+", contact_number = "+contact_number+"]";
    }
}