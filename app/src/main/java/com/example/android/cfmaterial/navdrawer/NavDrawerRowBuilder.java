package com.example.android.cfmaterial.navdrawer;

import com.example.android.cfmaterial.R;

public class NavDrawerRowBuilder {

    private NavDrawerRowBuilder() {
    }

    public static NavDrawerRow getDivider(int position) {
        return new NavDrawerRow.Builder(NavDrawerRow.Type.DIVIDER, position).build();
    }

    public static NavDrawerRow getHeader(String text, int position) {
        return new NavDrawerRow.Builder(NavDrawerRow.Type.HEADER, position)
                .setText(text).build();
    }

    public static NavDrawerRow getItem(NavDrawerRow.Action action, int position) {
        NavDrawerRow row;
        switch (action) {
            case LOGIN:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM, position).setText("Login").setIcon(R.drawable.ic_person_black_24dp).setAction(action).build();
                break;
            case RETAILERS_SHOW_ALL:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM, position).setText("All").setIcon(R.drawable.ic_local_grocery_store_black_24dp).setAction(action).build();
                break;
            case RETAILERS_SHOW_FAV:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM, position).setText("Favorites").setIcon(R.drawable.ic_favorite_black_24dp).setAction(action).build();
                break;
            case RETAILERS_SHOW_NEAR:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM, position).setText("Nearby").setIcon(R.drawable.ic_my_location_black_24dp).setAction(action).build();
                break;
            case OFFERS:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM, position).setText("Offers").setIcon(R.drawable.ic_shopping_basket_black_24dp).setAction(action).build();
                break;
            case ADD_CARD:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM, position).setText("Add Card").setIcon(R.drawable.ic_card_membership_black_24dp).setAction(action).build();
                break;
            case MY_CARD:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM, position).setText("My Card").setIcon(R.drawable.ic_card_membership_black_24dp).setAction(action).build();
                break;
            case STORES:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM, position).setText("Stores").setIcon(R.drawable.ic_map_black_24dp).setAction(action).build();
                break;
            case HISTORY:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM, position).setText("History").setIcon(R.drawable.ic_history_black_24dp).setAction(action).build();
                break;
            case SETTINGS:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM, position).setText("Settings").setIcon(R.drawable.ic_settings_black_24dp).setAction(action).build();
                break;
            case HELP:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM, position).setText("Help").setIcon(R.drawable.ic_help_outline_black_24dp).setAction(action).build();
                break;
            case ABOUT:
                row = new NavDrawerRow.Builder(NavDrawerRow.Type.ITEM, position).setText("About").setIcon(R.drawable.ic_info_outline_black_24dp).setAction(action).build();
                break;
            case DEFAULT:
            default:
                row = getDivider(position);
                break;
        }
        return row;
    }

}
