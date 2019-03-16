package com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData;

import com.google.gson.annotations.SerializedName;

public class BannerItem{

	@SerializedName("updated_on")
	private String updatedOn;

	@SerializedName("image")
	private String image;

	@SerializedName("banner_id")
	private String bannerId;

	@SerializedName("position")
	private String position;

	@SerializedName("added_on")
	private String addedOn;

	@SerializedName("url")
	private String url;

	public void setUpdatedOn(String updatedOn){
		this.updatedOn = updatedOn;
	}

	public String getUpdatedOn(){
		return updatedOn;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setBannerId(String bannerId){
		this.bannerId = bannerId;
	}

	public String getBannerId(){
		return bannerId;
	}

	public void setPosition(String position){
		this.position = position;
	}

	public String getPosition(){
		return position;
	}

	public void setAddedOn(String addedOn){
		this.addedOn = addedOn;
	}

	public String getAddedOn(){
		return addedOn;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"BannerItem{" + 
			"updated_on = '" + updatedOn + '\'' + 
			",image = '" + image + '\'' + 
			",banner_id = '" + bannerId + '\'' + 
			",position = '" + position + '\'' + 
			",added_on = '" + addedOn + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}