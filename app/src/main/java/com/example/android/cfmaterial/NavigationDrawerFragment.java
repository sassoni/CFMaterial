package com.example.android.cfmaterial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class NavigationDrawerFragment extends Fragment {

    protected static final String NAV_DRAWER_POSITION_KEY = "nav_drawer_position_key";

    protected int positionInNavDrawer = 0;

    abstract public void setupNavigationDrawer();
    abstract public void setupToolbar();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupNavigationDrawer();
        setupToolbar();
    }

}
