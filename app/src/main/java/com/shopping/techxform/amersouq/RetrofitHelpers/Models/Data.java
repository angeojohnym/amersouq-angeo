package com.shopping.techxform.amersouq.RetrofitHelpers.Models;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("date")
	private String date;

	@SerializedName("product_id")
	private int productId;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"date = '" + date + '\'' + 
			",product_id = '" + productId + '\'' + 
			"}";
		}
}