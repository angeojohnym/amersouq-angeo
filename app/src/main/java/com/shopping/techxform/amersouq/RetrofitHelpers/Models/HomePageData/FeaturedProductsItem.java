package com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData;

import com.google.gson.annotations.SerializedName;

public class FeaturedProductsItem{

	@SerializedName("short_description")
	private String shortDescription;

	@SerializedName("image")
	private String image;

	@SerializedName("price")
	private String price;

	@SerializedName("product_id")
	private String productId;

	@SerializedName("title")
	private String title;

	@SerializedName("offer_price")
	private String offerPrice;

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

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
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
			"FeaturedProductsItem{" + 
			"short_description = '" + shortDescription + '\'' + 
			",image = '" + image + '\'' + 
			",price = '" + price + '\'' + 
			",product_id = '" + productId + '\'' + 
			",title = '" + title + '\'' + 
			",offer_price = '" + offerPrice + '\'' + 
			"}";
		}
}