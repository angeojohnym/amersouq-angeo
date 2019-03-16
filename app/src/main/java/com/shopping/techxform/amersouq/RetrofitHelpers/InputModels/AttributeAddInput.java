
package com.shopping.techxform.amersouq.RetrofitHelpers.InputModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttributeAddInput {

    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("attr_name")
    @Expose
    private String attrName;
    @SerializedName("attr_type")
    @Expose
    private String attrType;

    /**
     * No args constructor for use in serialization
     * 
     */

    /**
     * 
     * @param userid
     * @param attrType
     * @param catId
     * @param attrName
     */
    public AttributeAddInput(String userid, String catId, String attrName, String attrType) {
        super();
        this.userid = userid;
        this.catId = catId;
        this.attrName = attrName;
        this.attrType = attrType;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

}
