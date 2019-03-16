package com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_set;

import com.google.gson.annotations.SerializedName;

public class OptionsListItem{

	@SerializedName("option_value")
	private String optionValue;

	@SerializedName("option_id")
	private String optionId;

	public void setOptionValue(String optionValue){
		this.optionValue = optionValue;
	}

	public String getOptionValue(){
		return optionValue;
	}

	public void setOptionId(String optionId){
		this.optionId = optionId;
	}

	public String getOptionId(){
		return optionId;
	}

	@Override
 	public String toString(){
		return 
			"OptionsListItem{" + 
			"option_value = '" + optionValue + '\'' + 
			",option_id = '" + optionId + '\'' + 
			"}";
		}
}