package com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData;

import com.google.gson.annotations.SerializedName;

public class ForsaleCategoryItem{

	@SerializedName("updated_on")
	private String updatedOn;

	@SerializedName("is_product")
	private String isProduct;

	@SerializedName("image")
	private String image;

	@SerializedName("category_name")
	private String categoryName;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("is_auction")
	private String isAuction;

	@SerializedName("is_classified")
	private String isClassified;

	@SerializedName("added_on")
	private String addedOn;

	@SerializedName("is_fixed_price")
	private String isFixedPrice;

	@SerializedName("slug")
	private String slug;


	public ForsaleCategoryItem( String categoryId,String categoryName,String updatedOn, String isProduct, String image,
							   String isAuction, String isClassified, String addedOn, String isFixedPrice, String slug) {
		this.updatedOn = updatedOn;
		this.isProduct = isProduct;
		this.image = image;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
		this.isAuction = isAuction;
		this.isClassified = isClassified;
		this.addedOn = addedOn;
		this.isFixedPrice = isFixedPrice;
		this.slug = slug;
	}

	public void setUpdatedOn(String updatedOn){
		this.updatedOn = updatedOn;
	}

	public String getUpdatedOn(){
		return updatedOn;
	}

	public void setIsProduct(String isProduct){
		this.isProduct = isProduct;
	}

	public String getIsProduct(){
		return isProduct;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setIsAuction(String isAuction){
		this.isAuction = isAuction;
	}

	public String getIsAuction(){
		return isAuction;
	}

	public void setIsClassified(String isClassified){
		this.isClassified = isClassified;
	}

	public String getIsClassified(){
		return isClassified;
	}

	public void setAddedOn(String addedOn){
		this.addedOn = addedOn;
	}

	public String getAddedOn(){
		return addedOn;
	}

	public void setIsFixedPrice(String isFixedPrice){
		this.isFixedPrice = isFixedPrice;
	}

	public String getIsFixedPrice(){
		return isFixedPrice;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	@Override
 	public String toString(){
		return 
			"ForsaleCategoryItem{" + 
			"updated_on = '" + updatedOn + '\'' + 
			",is_product = '" + isProduct + '\'' + 
			",image = '" + image + '\'' + 
			",category_name = '" + categoryName + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",is_auction = '" + isAuction + '\'' + 
			",is_classified = '" + isClassified + '\'' + 
			",added_on = '" + addedOn + '\'' + 
			",is_fixed_price = '" + isFixedPrice + '\'' + 
			",slug = '" + slug + '\'' + 
			"}";
		}
}