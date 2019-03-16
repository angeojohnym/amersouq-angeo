package com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData;

import com.google.gson.annotations.SerializedName;

public class AuctionsItem{

	@SerializedName("short_description")
	private String shortDescription;

	@SerializedName("image")
	private String image;

	@SerializedName("from_date")
	private String fromDate;

	@SerializedName("to_date")
	private String toDate;

	@SerializedName("price")
	private String price;

	@SerializedName("auction_ad_id")
	private String auctionAdId;

	@SerializedName("place")
	private String place;

	@SerializedName("state")
	private String state;

	@SerializedName("title")
	private String title;

	@SerializedName("current_bid")
	private String currentBid;

	@SerializedName("added_on")
	private String addedOn;

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

	public void setFromDate(String fromDate){
		this.fromDate = fromDate;
	}

	public String getFromDate(){
		return fromDate;
	}

	public void setToDate(String toDate){
		this.toDate = toDate;
	}

	public String getToDate(){
		return toDate;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setAuctionAdId(String auctionAdId){
		this.auctionAdId = auctionAdId;
	}

	public String getAuctionAdId(){
		return auctionAdId;
	}

	public void setPlace(String place){
		this.place = place;
	}

	public String getPlace(){
		return place;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setCurrentBid(String currentBid){
		this.currentBid = currentBid;
	}

	public String getCurrentBid(){
		return currentBid;
	}

	public void setAddedOn(String addedOn){
		this.addedOn = addedOn;
	}

	public String getAddedOn(){
		return addedOn;
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
			"AuctionsItem{" + 
			"short_description = '" + shortDescription + '\'' + 
			",image = '" + image + '\'' + 
			",from_date = '" + fromDate + '\'' + 
			",to_date = '" + toDate + '\'' + 
			",price = '" + price + '\'' + 
			",auction_ad_id = '" + auctionAdId + '\'' + 
			",place = '" + place + '\'' + 
			",state = '" + state + '\'' + 
			",title = '" + title + '\'' + 
			",current_bid = '" + currentBid + '\'' + 
			",added_on = '" + addedOn + '\'' + 
			",offer_price = '" + offerPrice + '\'' + 
			"}";
		}
}