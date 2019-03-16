package com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_set;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AttributeSetOutput{

	@SerializedName("code")
	private String code;

	@SerializedName("attributes")
	private List<AttributesItem> attributes;

	@SerializedName("message")
	private String message;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setAttributes(List<AttributesItem> attributes){
		this.attributes = attributes;
	}

	public List<AttributesItem> getAttributes(){
		return attributes;
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
			"AttributeSetOutput{" + 
			"code = '" + code + '\'' + 
			",attributes = '" + attributes + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}