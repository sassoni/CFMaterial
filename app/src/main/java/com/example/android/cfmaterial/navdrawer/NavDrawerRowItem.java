package com.example.android.cfmaterial.navdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.cfmaterial.R;

public class NavDrawerRowItem extends NavDrawerRow {
    private String title;
    private int iconId;

    public NavDrawerRowItem(String title, int iconId) {
        this.title = title;
        this.iconId = iconId;
    }

    @Override
    public View inflateView(Context context, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.nav_drawer_row_item, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.nav_drawer_row_item_text);
        textView.setText(title);
        textView.setCompoundDrawablesWithIntrinsicBounds(iconId, 0, 0, 0);
        return view;
    }
}
