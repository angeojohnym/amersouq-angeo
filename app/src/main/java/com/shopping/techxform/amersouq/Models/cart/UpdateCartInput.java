package com.shopping.techxform.amersouq.Models.cart;

import com.google.gson.annotations.SerializedName;

public class UpdateCartInput{

	@SerializedName("cart_id")
	private String cartId;

	@SerializedName("quantity")
	private String quantity;

	@SerializedName("product_id")
	private String productId;


	public UpdateCartInput(String cartId, String quantity, String productId) {
		this.cartId = cartId;
		this.quantity = quantity;
		this.productId = productId;
	}

	public void setCartId(String cartId){
		this.cartId = cartId;
	}

	public String getCartId(){
		return cartId;
	}

	public void setQuantity(String quantity){
		this.quantity = quantity;
	}

	public String getQuantity(){
		return quantity;
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
			"UpdateCartInput{" + 
			"cart_id = '" + cartId + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",product_id = '" + productId + '\'' + 
			"}";
		}
}