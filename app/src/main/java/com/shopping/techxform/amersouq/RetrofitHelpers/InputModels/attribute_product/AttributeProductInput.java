package com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.attribute_product;

import com.google.gson.annotations.SerializedName;

public class AttributeProductInput{

	@SerializedName("attribute_id")
	private String attributeId;

	@SerializedName("product_id")
	private String productId;

	@SerializedName("option")
	private String option;

	public AttributeProductInput(String attributeId, String productId, String option) {
		this.attributeId = attributeId;
		this.productId = productId;
		this.option = option;
	}

	public void setAttributeId(String attributeId){
		this.attributeId = attributeId;
	}

	public String getAttributeId(){
		return attributeId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setOption(String option){
		this.option = option;
	}

	public String getOption(){
		return option;
	}

	@Override
 	public String toString(){
		return 
			"AttributeProductInput{" + 
			"attribute_id = '" + attributeId + '\'' + 
			",product_id = '" + productId + '\'' + 
			",option = '" + option + '\'' + 
			"}";
		}
}