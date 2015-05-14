package com.example.android.cfmaterial.navdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.cfmaterial.R;

public class NavDrawerRowHeader extends NavDrawerRow {
    private String title;

    public NavDrawerRowHeader(String title) {
        this.title = title;
    }

    @Override
    public View inflateView(Context context, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.nav_drawer_row_header, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.nav_drawer_row_header_text);
        textView.setText(title);
        return view;
    }
}
