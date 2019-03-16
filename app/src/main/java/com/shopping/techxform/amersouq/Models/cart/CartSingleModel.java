package com.shopping.techxform.amersouq.Models.cart;

import com.google.gson.annotations.SerializedName;

public class CartSingleModel {

	@SerializedName("seller")
	private String seller;

	@SerializedName("image")
	private String image;

	@SerializedName("total")
	private String total;

	@SerializedName("quantity")
	private String quantity;

	@SerializedName("price")
	private String price;

	@SerializedName("product_id")
	private String productId;

	@SerializedName("title")
	private String title;

	@SerializedName("offer_price")
	private String offerPrice;

	public void setSeller(String seller){
		this.seller = seller;
	}

	public String getSeller(){
		return seller;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	public void setQuantity(String quantity){
		this.quantity = quantity;
	}

	public String getQuantity(){
		return quantity;
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
			"CartSingleModel{" +
			"seller = '" + seller + '\'' + 
			",image = '" + image + '\'' + 
			",total = '" + total + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",price = '" + price + '\'' + 
			",product_id = '" + productId + '\'' + 
			",title = '" + title + '\'' + 
			",offer_price = '" + offerPrice + '\'' + 
			"}";
		}
}