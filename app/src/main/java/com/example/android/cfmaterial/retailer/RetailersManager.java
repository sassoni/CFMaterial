package com.example.android.cfmaterial.retailer;

import android.content.Context;

import com.example.android.cfmaterial.R;

import java.util.ArrayList;
import java.util.List;

public class RetailersManager {

    private Context context;

    public RetailersManager(Context context) {
        this.context = context;
    }

    public List<Retailer> getAllRetailers() {
        // Download and cache
        Retailer kroger = new Retailer("Kroger", R.drawable.i_kroger);
        kroger.addCard("123456789");
        Retailer marsh = new Retailer("Marsh", R.drawable.i_marsh);
        marsh.addCard("987654321");
        List<Retailer> allRetailers = new ArrayList<>();
        allRetailers.add(new Retailer("A&P", R.drawable.i_aandp));
        allRetailers.add(new Retailer("Family Express", R.drawable.i_familyexpress));
        allRetailers.add(new Retailer("Giant Carlisle", R.drawable.i_gc));
        allRetailers.add(new Retailer("Giant Landover", R.drawable.i_gl));
        allRetailers.add(new Retailer("Harris Teeter", R.drawable.i_harristeeter));
        allRetailers.add(kroger);
        allRetailers.add(marsh);
        allRetailers.add(new Retailer("Martin's", R.drawable.i_martins));
        allRetailers.add(new Retailer("Safeway", R.drawable.i_safeway));
        allRetailers.add(new Retailer("ShopRite", R.drawable.i_shoprite));
        allRetailers.add(new Retailer("Stop&Shop", R.drawable.i_stopandshop));
        allRetailers.add(new Retailer("Walmart", R.drawable.i_walmart));
        allRetailers.add(new Retailer("Weis", R.drawable.i_weis));
        return allRetailers;
    }

    public List<Retailer> getFavoriteRetailers() {
        // Download and cache
        List<Retailer> favoriteRetailers = new ArrayList<>();
        favoriteRetailers.add(getAllRetailers().get(5));
        favoriteRetailers.add(getAllRetailers().get(6));
        return favoriteRetailers;
    }

    public List<Retailer> getNearbyRetailers() {
        // Download and cache
        List<Retailer> nearbyRetailers = new ArrayList<>();
        nearbyRetailers.add(getAllRetailers().get(5));
        nearbyRetailers.add(getAllRetailers().get(7));
        nearbyRetailers.add(getAllRetailers().get(8));
        nearbyRetailers.add(getAllRetailers().get(9));
        return nearbyRetailers;
    }

}
