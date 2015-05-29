package com.example.android.cfmaterial.tabsfragments;

import java.util.List;

/**
 * Created by ravatapa on 5/28/2015.
 */
public class History {

    private List<Offers> offersList;
    private String date;
    private String savings;
    private boolean isExpanded = false;

    public List<Offers> getOffersList() {
        return offersList;
    }

    public void setOffersList(List<Offers> offersList) {
        this.offersList = offersList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSavings() {
        return savings;
    }

    public void setSavings(String savings) {
        this.savings = savings;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setIsExpanded(boolean isExpanded) {
        this.isExpanded = isExpanded;
    }
}
