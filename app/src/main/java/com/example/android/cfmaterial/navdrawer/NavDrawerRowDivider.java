package com.example.android.cfmaterial.navdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.cfmaterial.R;

public class NavDrawerRowDivider extends NavDrawerRow {
    @Override
    View inflateView(Context context, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.nav_drawer_row_divider, parent, false);
    }
}
