package com.shopping.techxform.amersouq.RetrofitHelpers.Models.bidoutput;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("date")
	private String date;

	@SerializedName("bid_id")
	private int bidId;

	@SerializedName("bid_price")
	private String bidPrice;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setBidId(int bidId){
		this.bidId = bidId;
	}

	public int getBidId(){
		return bidId;
	}

	public void setBidPrice(String bidPrice){
		this.bidPrice = bidPrice;
	}

	public String getBidPrice(){
		return bidPrice;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"date = '" + date + '\'' + 
			",bid_id = '" + bidId + '\'' + 
			",bid_price = '" + bidPrice + '\'' + 
			"}";
		}
}