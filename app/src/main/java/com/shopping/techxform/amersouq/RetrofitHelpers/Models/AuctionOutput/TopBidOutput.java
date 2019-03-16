package com.shopping.techxform.amersouq.RetrofitHelpers.Models.AuctionOutput;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TopBidOutput{

	@SerializedName("code")
	private String code;

	@SerializedName("alert")
	private List<AlertItem> alert;

	@SerializedName("message")
	private String message;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setAlert(List<AlertItem> alert){
		this.alert = alert;
	}

	public List<AlertItem> getAlert(){
		return alert;
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
			"TopBidOutput{" + 
			"code = '" + code + '\'' + 
			",alert = '" + alert + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}