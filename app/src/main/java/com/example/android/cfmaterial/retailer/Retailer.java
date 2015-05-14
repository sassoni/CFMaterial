package com.example.android.cfmaterial.retailer;

public class Retailer {
    private String name;
    private int drawableId;

    public Retailer(String name, int drawableId) {
        this.name = name;
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public int getDrawableId() {
        return drawableId;
    }
}
