package com.shopping.techxform.amersouq.RetrofitHelpers.Models.profile_image_out;

import com.google.gson.annotations.SerializedName;

public class ProfileImageOutput{

	@SerializedName("code")
	private String code;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("image_url")
	private String imageUrl;

	@SerializedName("message")
	private String message;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
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
			"ProfileImageOutput{" + 
			"code = '" + code + '\'' + 
			",user_id = '" + userId + '\'' + 
			",image_url = '" + imageUrl + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}