package com.example.android.cfmaterial;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.android.cfmaterial.offer.RetailerOffersFragment;
import com.example.android.cfmaterial.offer.SavedOffersFragment;

public class TabsViewPagerAdapter extends FragmentPagerAdapter {

    String[] tabNames = new String[]{"RETAILER OFFERS", "SAVED OFFERS"};

    public TabsViewPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.i("TABSADAPTER", "constructor");
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RetailerOffersFragment.newInstance("e");
            case 1:
                return SavedOffersFragment.newInstance("e");
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}
