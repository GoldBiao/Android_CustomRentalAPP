package com.keysu.customizedrental.entity;

import java.io.Serializable;

public class HouseList implements Serializable {

    private int headImage;

    private String houseAddress;

    @Override
    public String toString() {
        return "HouseList{" +
                "headImage=" + headImage +
                ", houseAddress='" + houseAddress + '\'' +
                ", houseAcreage='" + houseAcreage + '\'' +
                ", houseIntro='" + houseIntro + '\'' +
                ", rentalYears='" + rentalYears + '\'' +
                ", price=" + price +
                ", isAgency=" + isAgency +
                '}';
    }

    public HouseList(int headImage, String houseAddress, String houseAcreage, String houseIntro,
                     String rentalYears, float price, boolean isAgency) {
        this.headImage = headImage;
        this.houseAddress = houseAddress;
        this.houseAcreage = houseAcreage;
        this.houseIntro = houseIntro;
        this.rentalYears = rentalYears;
        this.price = price;
        this.isAgency = isAgency;
    }

    private String houseAcreage;

    private String houseIntro;

    private String rentalYears;

    private float price;

    private boolean isAgency;


    public int getHeadImage() {
        return headImage;
    }

    public void setHeadImage(int headImage) {
        this.headImage = headImage;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseAcreage() {
        return houseAcreage;
    }

    public void setHouseAcreage(String houseAcreage) {
        this.houseAcreage = houseAcreage;
    }

    public String getHouseIntro() {
        return houseIntro;
    }

    public void setHouseIntro(String houseIntro) {
        this.houseIntro = houseIntro;
    }

    public String getRentalYears() {
        return rentalYears;
    }

    public void setRentalYears(String rentalYears) {
        this.rentalYears = rentalYears;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean getIsAgency() {
        return isAgency;
    }

    public void setIsAgency(boolean isAgency) {
        this.isAgency = isAgency;
    }


}
