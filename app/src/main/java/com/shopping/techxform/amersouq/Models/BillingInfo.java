package com.shopping.techxform.amersouq.Models;

public class BillingInfo {

    private String name;

    private String card_number;

    private String mm;

    private String yy;

    public BillingInfo(String name, String card_number, String mm, String yy) {
        this.name = name;
        this.card_number = card_number;
        this.mm = mm;
        this.yy = yy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getYy() {
        return yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }
}
