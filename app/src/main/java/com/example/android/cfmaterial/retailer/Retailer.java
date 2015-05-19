package com.example.android.cfmaterial.retailer;

import android.os.Parcel;
import android.os.Parcelable;

public class Retailer implements Parcelable{
    private String name;
    private int drawableId;

    public Retailer(String name, int drawableId) {
        this.name = name;
        this.drawableId = drawableId;
    }

    private Retailer(Parcel in) {
        name = in.readString();
        drawableId = in.readInt();
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
    }

    public String getName() {
        return name;
    }

    public int getDrawableId() {
        return drawableId;
    }
}
