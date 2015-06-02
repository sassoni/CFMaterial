package com.example.android.cfmaterial;

import android.support.v4.app.Fragment;

public abstract class NavigationDrawerFragment extends Fragment {

    protected int positionInNavDrawer = 0;

    abstract public void setupNavigationDrawer();

    abstract public void setupToolbar();

    @Override
    public void onResume() {
        super.onResume();
        setupNavigationDrawer();

        setupToolbar();
    }
}
