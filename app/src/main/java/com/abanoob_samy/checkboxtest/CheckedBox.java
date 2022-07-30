package com.abanoob_samy.checkboxtest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckedBox {

    @Expose
    @SerializedName("carName")
    private String carName;

    @Expose
    @SerializedName("price")
    private String price;

    public CheckedBox(String carName, String price) {
        this.carName = carName;
        this.price = price;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
