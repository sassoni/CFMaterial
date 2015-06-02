package com.example.android.cfmaterial.navdrawer;

import com.example.android.cfmaterial.retailer.Retailer;

import java.util.HashMap;
import java.util.Map;

public class NavDrawerManager {

    private static NavDrawerManager instance;
    private static Map<NavDrawerRow, Integer> positions;

    private NavDrawerManager() {
    }

    public NavDrawerManager getInstance() {
        if (instance == null) {
            instance = new NavDrawerManager();
        }
        if (positions == null) {
            positions = new HashMap<>();
        }
        return instance;
    }

    public void populate(NavDrawerAdapter adapter) {
//        adapter.addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.LOGIN));
//        adapter.addRow(NavDrawerRowBuilder.getDivider());
//        adapter.addRow(NavDrawerRowBuilder.getHeader("Retailers"));
//        adapter.addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_ALL));
//        adapter.addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_FAV));
//        adapter.addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_NEAR));
//        adapter.addRow(NavDrawerRowBuilder.getDivider());
//        adapter.addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.SETTINGS));
//        adapter.addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.HELP));
//        adapter.addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.ABOUT));
    }


    public void populate(NavDrawerAdapter adapter, Retailer retailer) {

    }

}
