package com.example.android.cfmaterial.navdrawer;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.android.cfmaterial.R;

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
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.LOGIN));
        addRow(NavDrawerRowBuilder.getDivider());
        addRow(NavDrawerRowBuilder.getHeader("Retailers"));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_ALL));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_FAV));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_NEAR));
        addRow(NavDrawerRowBuilder.getDivider());
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.SETTINGS));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.HELP));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.ABOUT));
    }

    public void populate(String retailerName) {
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.LOGIN));
        addRow(NavDrawerRowBuilder.getDivider());
        addRow(NavDrawerRowBuilder.getHeader(retailerName));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.OFFERS));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.CARD));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.STORES));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.HISTORY));
        addRow(NavDrawerRowBuilder.getDivider());
        addRow(NavDrawerRowBuilder.getHeader("Retailers"));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_ALL));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_FAV));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.RETAILERS_SHOW_NEAR));
        addRow(NavDrawerRowBuilder.getDivider());
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.SETTINGS));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.HELP));
        addRow(NavDrawerRowBuilder.getItem(NavDrawerRow.Action.ABOUT));
    }
}
