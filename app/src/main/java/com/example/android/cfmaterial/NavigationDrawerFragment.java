package com.example.android.cfmaterial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class NavigationDrawerFragment extends Fragment {

    abstract public void setupNavigationDrawer();
    abstract public void setupToolbar();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupNavigationDrawer();
        setupToolbar();
    }
}
