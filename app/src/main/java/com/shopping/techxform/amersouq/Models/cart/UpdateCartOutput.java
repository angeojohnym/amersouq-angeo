package com.shopping.techxform.amersouq.Models.cart;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UpdateCartOutput{

	@SerializedName("code")
	private String code;

	@SerializedName("message")
	private String message;

	@SerializedName("cart")
	private List<CartItem> cart;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCart(List<CartItem> cart){
		this.cart = cart;
	}

	public List<CartItem> getCart(){
		return cart;
	}

	@Override
 	public String toString(){
		return 
			"UpdateCartOutput{" + 
			"code = '" + code + '\'' + 
			",message = '" + message + '\'' + 
			",cart = '" + cart + '\'' + 
			"}";
		}
}