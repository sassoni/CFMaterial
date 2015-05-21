package com.example.android.cfmaterial.tabsfragments;

import java.io.Serializable;

/**
 * Created by rohithavatapally on 8/28/14.
 */
public class Offers implements Serializable {

    private static final long serialVersionUID = 564971444;

    private int id;
    private String heading;
    private String description;
    private String detailDescription;
    private String terms;
    private String expiration;
    private boolean isClipped = false;
    private boolean isClipping = false;
    private boolean isExpanded = false;
    private boolean isOfferSelected = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public boolean isClipped() {
        return isClipped;
    }

    public void setClipped(boolean isClipped) {
        this.isClipped = isClipped;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setIsExpanded(boolean isExpanded) {
        this.isExpanded = isExpanded;
    }

    public boolean isClipping() {
        return isClipping;
    }

    public void setIsClipping(boolean isClipping) {
        this.isClipping = isClipping;
    }

    public boolean isOfferSelected() {
        return isOfferSelected;
    }

    public void setIsOfferSelected(boolean isOfferSelected) {
        this.isOfferSelected = isOfferSelected;
    }
}


