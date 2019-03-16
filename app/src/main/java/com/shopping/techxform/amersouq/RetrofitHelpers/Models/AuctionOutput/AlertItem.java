package com.shopping.techxform.amersouq.RetrofitHelpers.Models.AuctionOutput;

import com.google.gson.annotations.SerializedName;

public class AlertItem{

	@SerializedName("id")
	private String id;

	@SerializedName("bid_price")
	private String bidPrice;

	@SerializedName("status")
	private String status;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setBidPrice(String bidPrice){
		this.bidPrice = bidPrice;
	}

	public String getBidPrice(){
		return bidPrice;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"AlertItem{" + 
			"id = '" + id + '\'' + 
			",bid_price = '" + bidPrice + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}