package com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_set;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AttributesItem{

	@SerializedName("attribute_id")
	private String attributeId;

	@SerializedName("options_list")
	private List<OptionsListItem> optionsList;

	@SerializedName("attributetype")
	private String attributetype;

	@SerializedName("attributename")
	private String attributename;

	@SerializedName("mandatory")
	private String mandatory;

	public void setAttributeId(String attributeId){
		this.attributeId = attributeId;
	}

	public String getAttributeId(){
		return attributeId;
	}

	public void setOptionsList(List<OptionsListItem> optionsList){
		this.optionsList = optionsList;
	}

	public List<OptionsListItem> getOptionsList(){
		return optionsList;
	}

	public void setAttributetype(String attributetype){
		this.attributetype = attributetype;
	}

	public String getAttributetype(){
		return attributetype;
	}

	public void setAttributename(String attributename){
		this.attributename = attributename;
	}

	public String getAttributename(){
		return attributename;
	}

	public void setMandatory(String mandatory){
		this.mandatory = mandatory;
	}

	public String getMandatory(){
		return mandatory;
	}

	@Override
 	public String toString(){
		return 
			"AttributesItem{" + 
			"attribute_id = '" + attributeId + '\'' + 
			",options_list = '" + optionsList + '\'' + 
			",attributetype = '" + attributetype + '\'' + 
			",attributename = '" + attributename + '\'' + 
			",mandatory = '" + mandatory + '\'' + 
			"}";
		}
}