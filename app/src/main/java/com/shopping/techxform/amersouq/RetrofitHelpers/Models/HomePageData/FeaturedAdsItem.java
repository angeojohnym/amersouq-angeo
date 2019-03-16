package com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData;

import com.google.gson.annotations.SerializedName;

public class FeaturedAdsItem{

	@SerializedName("ad_type_id")
	private String adTypeId;

	@SerializedName("short_description")
	private String shortDescription;

	@SerializedName("image")
	private String image;

	@SerializedName("ad_id")
	private String adId;

	@SerializedName("price")
	private String price;

	@SerializedName("title")
	private String title;

	@SerializedName("offer_price")
	private String offerPrice;

	public void setAdTypeId(String adTypeId){
		this.adTypeId = adTypeId;
	}

	public String getAdTypeId(){
		return adTypeId;
	}

	public void setShortDescription(String shortDescription){
		this.shortDescription = shortDescription;
	}

	public String getShortDescription(){
		return shortDescription;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setAdId(String adId){
		this.adId = adId;
	}

	public String getAdId(){
		return adId;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setOfferPrice(String offerPrice){
		this.offerPrice = offerPrice;
	}

	public String getOfferPrice(){
		return offerPrice;
	}

	@Override
 	public String toString(){
		return 
			"FeaturedAdsItem{" + 
			"ad_type_id = '" + adTypeId + '\'' + 
			",short_description = '" + shortDescription + '\'' + 
			",image = '" + image + '\'' + 
			",ad_id = '" + adId + '\'' + 
			",price = '" + price + '\'' + 
			",title = '" + title + '\'' + 
			",offer_price = '" + offerPrice + '\'' + 
			"}";
		}
}