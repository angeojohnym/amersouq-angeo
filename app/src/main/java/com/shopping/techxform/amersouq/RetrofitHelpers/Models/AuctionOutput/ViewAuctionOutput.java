
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.AuctionOutput;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewAuctionOutput {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("auction")
    @Expose
    private Auction auction;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

}
