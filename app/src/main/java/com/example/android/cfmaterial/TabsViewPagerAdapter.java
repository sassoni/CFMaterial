package com.example.android.cfmaterial;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ravatapa on 5/6/2015.
 */
public class TabsViewPagerAdapter extends FragmentPagerAdapter {

    private final int COUNT;
    private final String[] fragmentsTitles;
    private final Fragment[] fragments;

    public TabsViewPagerAdapter(FragmentManager fm, int COUNT, String[] fragmentsTitles, Fragment[] fragments) {
        super(fm);
        this.COUNT = COUNT;
        this.fragmentsTitles = fragmentsTitles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsTitles[position];
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
