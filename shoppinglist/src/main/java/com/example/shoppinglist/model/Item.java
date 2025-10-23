package com.example.shoppinglist.model;

public class Item {
    private String product;
    private String amount;

    public Item() {}

    public Item(String product, String amount) {
        this.product = product;
        this.amount = amount;
    }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }
}
