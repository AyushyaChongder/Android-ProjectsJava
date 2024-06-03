package com.example.coffeeapp;

public class Model {
        String itemName;
    String itemDescription;
    int itemImg;

    public Model(String itemName, String itemDescription, int itemImg) {
        this.itemName = itemName;
        this.itemImg = itemImg;
        this.itemDescription=itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getItemImg() {
        return itemImg;
    }

    public String getItemDescription() {
        return itemDescription;
    }

}

