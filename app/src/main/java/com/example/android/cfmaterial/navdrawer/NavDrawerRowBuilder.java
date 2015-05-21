package com.example.android.cfmaterial.navdrawer;

import com.example.android.cfmaterial.R;

public class NavDrawerRowBuilder {

    private NavDrawerRowBuilder() {
    }

    public static NavDrawerRow getDivider() {
        return new NavDrawerRow.Builder(NavDrawerRow.Type.DIVIDER).build();
    }

    public static NavDrawerRow getHeader(String text) {
        return new NavDrawerRow.Builder(NavDrawerRow.Type.HEADER)
                .setText(text).build();
    }

    public static NavDrawerRow getItem(NavDrawerRow.Action action) {
        NavDrawerRow row;
        switch (action) {
            case LOGIN:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM).setText("Login").setIcon(R.drawable.ic_action_accounts).setAction(action).build();
                break;
            case RETAILERS_SHOW_ALL:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM).setText("All").setIcon(R.drawable.ic_action_view_as_grid).setAction(action).build();
                break;
            case RETAILERS_SHOW_FAV:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM).setText("Favorites").setIcon(R.drawable.ic_action_favorite).setAction(action).build();
                break;
            case RETAILERS_SHOW_NEAR:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM).setText("Nearby").setIcon(R.drawable.ic_action_place).setAction(action).build();
                break;
            case OFFERS:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM).setText("Offers").setIcon(R.drawable.ic_action_view_as_list).setAction(action).build();
                break;
            case CARD:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM).setText("Card").setIcon(R.drawable.ic_action_labels).setAction(action).build();
                break;
            case STORES:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM).setText("Stores").setIcon(R.drawable.ic_action_map).setAction(action).build();
                break;
            case HISTORY:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM).setText("History").setIcon(R.drawable.ic_action_go_to_today).setAction(action).build();
                break;
            case SETTINGS:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM).setText("Settings").setIcon(R.drawable.ic_action_settings).setAction(action).build();
                break;
            case HELP:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM).setText("Help").setIcon(R.drawable.ic_action_help).setAction(action).build();
                break;
            case ABOUT:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM).setText("About").setIcon(R.drawable.ic_action_about).setAction(action).build();
                break;
            case DEFAULT:
            default:
                row = getDivider();
                break;
        }
        return row;
    }

}
