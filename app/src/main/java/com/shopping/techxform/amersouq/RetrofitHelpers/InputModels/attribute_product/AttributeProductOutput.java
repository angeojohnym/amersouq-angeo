package com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.attribute_product;

import com.google.gson.annotations.SerializedName;

public class AttributeProductOutput{

	@SerializedName("code")
	private String code;

	@SerializedName("product_id")
	private String productId;

	@SerializedName("message")
	private String message;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"AttributeProductOutput{" + 
			"code = '" + code + '\'' + 
			",product_id = '" + productId + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}