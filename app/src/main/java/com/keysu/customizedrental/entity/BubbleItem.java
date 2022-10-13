package com.keysu.customizedrental.entity;

public class BubbleItem {

    private String houseIntro;
    private String houseAcreage;
    private float housePrice;
    private String houseRentalyears;
    private boolean isAgency;


    public BubbleItem(String houseIntro, String houseAcreage, float housePrice, String houseRentalyears, boolean isAgency) {
        this.houseIntro = houseIntro;
        this.houseAcreage = houseAcreage;
        this.housePrice = housePrice;
        this.houseRentalyears = houseRentalyears;
        this.isAgency = isAgency;
    }

    public String getHouseIntro() {
        return houseIntro;
    }

    public void setHouseIntro(String houseIntro) {
        this.houseIntro = houseIntro;
    }

    public String getHouseAcreage() {
        return houseAcreage;
    }

    public void setHouseAcreage(String houseAcreage) {
        this.houseAcreage = houseAcreage;
    }

    public float getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(float housePrice) {
        this.housePrice = housePrice;
    }

    public String getHouseRentalyears() {
        return houseRentalyears;
    }

    public void setHouseRentalyears(String houseRentalyears) {
        this.houseRentalyears = houseRentalyears;
    }

    public boolean isAgency() {
        return isAgency;
    }

    public void setAgency(boolean agency) {
        isAgency = agency;
    }

    @Override
    public String toString() {
        return "BubbleItem{" +
                "houseIntro='" + houseIntro + '\'' +
                ", houseAcreage='" + houseAcreage + '\'' +
                ", housePrice=" + housePrice +
                ", houseRentalyears='" + houseRentalyears + '\'' +
                ", isAgency=" + isAgency +
                '}';
    }
}
