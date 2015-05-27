package com.example.android.cfmaterial.retailer;

import android.os.Parcel;
import android.os.Parcelable;

public class Retailer implements Parcelable {

//    public enum Code {
//        AP, FAMILY_EXPRESS, GIANT_CARLISLE, GIANT_LANDOVER, HARRIS_TEETER,
//        KROGER, MARSH, MARTINS, SAFEWAY, SHOPRITE, STOP_SHOP, WALMART, WEIS
//    }

    private String name;
    private int drawableId;
    private String card;

    public Retailer(String name, int drawableId) {
        this.name = name;
        this.drawableId = drawableId;
    }

    public void addCard(String card) {
        this.card = card;
    }

    public boolean hasCard() {
        return card != null;
    }

    private Retailer(Parcel in) {
        name = in.readString();
        drawableId = in.readInt();
        card = in.readString();
    }

    public static final Parcelable.Creator<Retailer> CREATOR
            = new Parcelable.Creator<Retailer>() {
        public Retailer createFromParcel(Parcel in) {
            return new Retailer(in);
        }

        public Retailer[] newArray(int size) {
            return new Retailer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(drawableId);
        if (card != null) {
            dest.writeString(card);
        }
    }

    public String getName() {
        return name;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public String getCard() {
        return card;
    }
}
