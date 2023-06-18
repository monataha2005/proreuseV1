package com.example.proreusev1;

import java.util.Date;

public class Info {
    String userName;
    private String productName;
    private String date;
    private float price;
    private String description;
    public Info(){

    }
    public Info(String userName ,String productName, String date, float price, String description) {
        this.userName = userName;
        this.productName = productName;
        this.date = date;
        this.price = price;
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Info{" +
                "productName='" + productName + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
