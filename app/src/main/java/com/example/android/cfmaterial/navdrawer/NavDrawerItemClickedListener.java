package com.example.android.cfmaterial.navdrawer;

public interface NavDrawerItemClickedListener {

    public enum NavDrawerItem {
        RET_ALL, RET_FAV, RET_NEAR, OFFERS, CARD, STORES, HIST, SETT, HELP, ABOUT
    }

    public void onNavDrawerItemClicked(NavDrawerItem item);
}
