package com.shopping.techxform.amersouq.Models.cart;

import com.google.gson.annotations.SerializedName;

public class DeleteCartInput{

	@SerializedName("cart_id")
	private String cartId;

	@SerializedName("product_id")
	private String productId;

	public DeleteCartInput(String cartId, String productId) {
		this.cartId = cartId;
		this.productId = productId;
	}

	public void setCartId(String cartId){
		this.cartId = cartId;
	}

	public String getCartId(){
		return cartId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	@Override
 	public String toString(){
		return 
			"DeleteCartInput{" + 
			"cart_id = '" + cartId + '\'' + 
			",product_id = '" + productId + '\'' + 
			"}";
		}
}