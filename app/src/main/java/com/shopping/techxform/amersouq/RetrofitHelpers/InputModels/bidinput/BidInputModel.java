package com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.bidinput;

import com.google.gson.annotations.SerializedName;

public class BidInputModel{

	@SerializedName("is_auto")
	private String isAuto;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("auction_id")
	private String auctionId;

	@SerializedName("increment")
	private String increment;

	@SerializedName("max_amount")
	private String maxAmount;

	@SerializedName("bid_price")
	private String bidPrice;


	public BidInputModel(String isAuto, String userId, String auctionId, String increment, String maxAmount, String bidPrice) {
		this.isAuto = isAuto;
		this.userId = userId;
		this.auctionId = auctionId;
		this.increment = increment;
		this.maxAmount = maxAmount;
		this.bidPrice = bidPrice;
	}

	public void setIsAuto(String isAuto){
		this.isAuto = isAuto;
	}

	public String getIsAuto(){
		return isAuto;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setAuctionId(String auctionId){
		this.auctionId = auctionId;
	}

	public String getAuctionId(){
		return auctionId;
	}

	public void setIncrement(String increment){
		this.increment = increment;
	}

	public String getIncrement(){
		return increment;
	}

	public void setMaxAmount(String maxAmount){
		this.maxAmount = maxAmount;
	}

	public String getMaxAmount(){
		return maxAmount;
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
			"BidInputModel{" + 
			"is_auto = '" + isAuto + '\'' + 
			",user_id = '" + userId + '\'' + 
			",auction_id = '" + auctionId + '\'' + 
			",increment = '" + increment + '\'' + 
			",max_amount = '" + maxAmount + '\'' + 
			",bid_price = '" + bidPrice + '\'' + 
			"}";
		}
}