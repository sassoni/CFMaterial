package com.example.android.cfmaterial.navdrawer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class NavDrawerAdapter extends ArrayAdapter<NavDrawerRow> {

    private Context context;

    public void addHeader(String title) {
        add(new NavDrawerRowHeader(title));
    }

    public void addItem(String title, int iconId) {
        add(new NavDrawerRowItem(title, iconId));
    }

    public void addDivider() {
        add(new NavDrawerRowDivider());
    }

    public NavDrawerAdapter(Context context) {
        super(context, 0);
        this.context = context;
    }

    // TODO Viewholders
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        NavDrawerRow row = getItem(position);

        // Header
        if (row instanceof NavDrawerRowHeader) {
            rowView = ((NavDrawerRowHeader) row).inflateView(context, parent);
            if (convertView.isSelected()) {
            }
        }
        // Item
        else if (row instanceof NavDrawerRowItem) {
            rowView = ((NavDrawerRowItem) row).inflateView(context, parent);
        }
        // Divider
        else {
            rowView = ((NavDrawerRowDivider) row).inflateView(context, parent);
        }

        return rowView;
    }
}
