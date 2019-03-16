package com.shopping.techxform.amersouq.Models.cart;

public class CartItem{
	private String cartId;
	private String dateCreated;

	public void setCartId(String cartId){
		this.cartId = cartId;
	}

	public String getCartId(){
		return cartId;
	}

	public void setDateCreated(String dateCreated){
		this.dateCreated = dateCreated;
	}

	public String getDateCreated(){
		return dateCreated;
	}

	@Override
 	public String toString(){
		return 
			"CartItem{" + 
			"cart_id = '" + cartId + '\'' + 
			",date_created = '" + dateCreated + '\'' + 
			"}";
		}
}
