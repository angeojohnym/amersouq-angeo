package com.shopping.techxform.amersouq.Models;

import com.google.gson.annotations.SerializedName;

public class ProductRequest
{
    @SerializedName("full_desc")
    public String full_desc;
    @SerializedName("address")
    public String address;
    @SerializedName("breadth")
    public String breadth;
    @SerializedName("latitude")
    public String latitude;
    @SerializedName("cityname")
    public String cityname;
    @SerializedName("created_user_id")
    public String created_user_id;
    @SerializedName("boost_status")
    public String boost_status;
    @SerializedName("length")
    public String length;
    @SerializedName("available_to_guest")
    public String available_to_guest;
    @SerializedName("weight")
    public String weight;
    @SerializedName("language")
    public String language;
    @SerializedName("featured_product")
    public String featured_product;
    @SerializedName("package_type")
    public String package_type;
    @SerializedName("product_name")
    public String product_name;
    @SerializedName("offer_price")
    public String offer_price;
    @SerializedName("condition")
    public String condition;
    @SerializedName("category_id")
    public String category_id;
    @SerializedName("price")
    public String price;
    @SerializedName("contact")
    public String contact;
    @SerializedName("short_desc")
    public String short_desc;
    @SerializedName("height")
    public String height;
    @SerializedName("longitude")
    public String longitude;

    public String getFull_desc ()
    {
        return full_desc;
    }

    public void setFull_desc (String full_desc)
    {
        this.full_desc = full_desc;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getBreadth ()
    {
        return breadth;
    }

    public void setBreadth (String breadth)
    {
        this.breadth = breadth;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getCityname ()
    {
        return cityname;
    }

    public void setCityname (String cityname)
    {
        this.cityname = cityname;
    }

    public String getCreated_user_id ()
    {
        return created_user_id;
    }

    public void setCreated_user_id (String created_user_id)
    {
        this.created_user_id = created_user_id;
    }

    public String getBoost_status ()
    {
        return boost_status;
    }

    public void setBoost_status (String boost_status)
    {
        this.boost_status = boost_status;
    }

    public String getLength ()
    {
        return length;
    }

    public void setLength (String length)
    {
        this.length = length;
    }

    public String getAvailable_to_guest ()
    {
        return available_to_guest;
    }

    public void setAvailable_to_guest (String available_to_guest)
    {
        this.available_to_guest = available_to_guest;
    }

    public String getWeight ()
    {
        return weight;
    }

    public void setWeight (String weight)
    {
        this.weight = weight;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getFeatured_product ()
    {
        return featured_product;
    }

    public void setFeatured_product (String featured_product)
    {
        this.featured_product = featured_product;
    }

    public String getPackage_type ()
    {
        return package_type;
    }

    public void setPackage_type (String package_type)
    {
        this.package_type = package_type;
    }

    public String getProduct_name ()
    {
        return product_name;
    }

    public void setProduct_name (String product_name)
    {
        this.product_name = product_name;
    }

    public String getOffer_price ()
    {
        return offer_price;
    }

    public void setOffer_price (String offer_price)
    {
        this.offer_price = offer_price;
    }

    public String getCondition ()
    {
        return condition;
    }

    public void setCondition (String condition)
    {
        this.condition = condition;
    }

    public String getCategory_id ()
    {
        return category_id;
    }

    public void setCategory_id (String category_id)
    {
        this.category_id = category_id;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getContact ()
    {
        return contact;
    }

    public void setContact (String contact)
    {
        this.contact = contact;
    }

    public String getShort_desc ()
    {
        return short_desc;
    }

    public void setShort_desc (String short_desc)
    {
        this.short_desc = short_desc;
    }

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [full_desc = "+full_desc+", address = "+address+", breadth = "+breadth+", latitude = "+latitude+", cityname = "+cityname+", created_user_id = "+created_user_id+", boost_status = "+boost_status+", length = "+length+", available_to_guest = "+available_to_guest+", weight = "+weight+", language = "+language+", featured_product = "+featured_product+", package_type = "+package_type+", product_name = "+product_name+", offer_price = "+offer_price+", condition = "+condition+", category_id = "+category_id+", price = "+price+", contact = "+contact+", short_desc = "+short_desc+", height = "+height+", longitude = "+longitude+"]";
    }
}