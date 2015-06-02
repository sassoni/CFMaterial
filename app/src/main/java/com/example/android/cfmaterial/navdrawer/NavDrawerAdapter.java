package com.example.android.cfmaterial.navdrawer;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.retailer.Retailer;

public class NavDrawerAdapter extends ArrayAdapter<NavDrawerRow> {

//    public enum Type {
//        GENERIC, SPECIFIC
//    }

    private Context context;
    private NavDrawerItemClickedListener listener;

    public void addRow(NavDrawerRow row) {
        add(row);
    }

    public NavDrawerAdapter(Context context, NavDrawerItemClickedListener listener) {
        super(context, 0);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final NavDrawerRow row = getItem(position);
        Log.i("ADAPTER", "Getting view");
        View rowView = row.inflateView(context, parent);
        return rowView;
    }

    public void populate() {
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.LOGIN, 0));
        addRow(NavDrawerRowBuilder.getDivider(1));
        addRow(NavDrawerRowBuilder.getHeader("Retailers", 2));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_ALL, 3));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_FAV, 4));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_NEAR, 5));
        addRow(NavDrawerRowBuilder.getDivider(6));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.SETTINGS, 7));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.HELP, 8));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.ABOUT, 9));
    }

    public void populate(Retailer retailer) {
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.LOGIN, 0));
        addRow(NavDrawerRowBuilder.getDivider(1));
        addRow(NavDrawerRowBuilder.getHeader(retailer.getName(), 2));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.OFFERS, 3));

        if (retailer.hasCard()) {
            addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.MY_CARD, 4));
        } else {
            addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.ADD_CARD, 4));
        }

        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.STORES, 5));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.HISTORY, 6));
        addRow(NavDrawerRowBuilder.getDivider(7));
        addRow(NavDrawerRowBuilder.getHeader("Retailers", 8));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_ALL, 9));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_FAV, 10));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_NEAR, 11));
        addRow(NavDrawerRowBuilder.getDivider(12));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.SETTINGS, 13));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.HELP, 14));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.ABOUT, 15));
    }
}
