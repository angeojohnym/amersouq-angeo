package com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HomepageOutput{

	@SerializedName("featured_ads")
	private List<FeaturedAdsItem> featuredAds;

	@SerializedName("forsale_category")
	private List<ForsaleCategoryItem> forsaleCategory;

	@SerializedName("code")
	private String code;

	@SerializedName("featured_products")
	private List<FeaturedProductsItem> featuredProducts;

	@SerializedName("live_auction")
	private List<LiveAuctionItem> liveAuction;

	@SerializedName("banner")
	private List<BannerItem> banner;

	@SerializedName("message")
	private String message;

	@SerializedName("auctions")
	private List<AuctionsItem> auctions;

	public void setFeaturedAds(List<FeaturedAdsItem> featuredAds){
		this.featuredAds = featuredAds;
	}

	public List<FeaturedAdsItem> getFeaturedAds(){
		return featuredAds;
	}

	public void setForsaleCategory(List<ForsaleCategoryItem> forsaleCategory){
		this.forsaleCategory = forsaleCategory;
	}

	public List<ForsaleCategoryItem> getForsaleCategory(){
		return forsaleCategory;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setFeaturedProducts(List<FeaturedProductsItem> featuredProducts){
		this.featuredProducts = featuredProducts;
	}

	public List<FeaturedProductsItem> getFeaturedProducts(){
		return featuredProducts;
	}

	public void setLiveAuction(List<LiveAuctionItem> liveAuction){
		this.liveAuction = liveAuction;
	}

	public List<LiveAuctionItem> getLiveAuction(){
		return liveAuction;
	}

	public void setBanner(List<BannerItem> banner){
		this.banner = banner;
	}

	public List<BannerItem> getBanner(){
		return banner;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setAuctions(List<AuctionsItem> auctions){
		this.auctions = auctions;
	}

	public List<AuctionsItem> getAuctions(){
		return auctions;
	}

	@Override
 	public String toString(){
		return 
			"HomepageOutput{" + 
			"featured_ads = '" + featuredAds + '\'' + 
			",forsale_category = '" + forsaleCategory + '\'' + 
			",code = '" + code + '\'' + 
			",featured_products = '" + featuredProducts + '\'' + 
			",live_auction = '" + liveAuction + '\'' + 
			",banner = '" + banner + '\'' + 
			",message = '" + message + '\'' + 
			",auctions = '" + auctions + '\'' + 
			"}";
		}
}