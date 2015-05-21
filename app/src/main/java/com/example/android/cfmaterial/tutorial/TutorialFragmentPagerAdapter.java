package com.example.android.cfmaterial.tutorial;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TutorialFragmentPagerAdapter extends FragmentPagerAdapter {

    public TutorialFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TutorialFragmentOne.newInstance();
            case 1:
                return TutorialFragmentTwo.newInstance();
            case 2:
                return TutorialFragmentThree.newInstance();
            default:
                return null;
        }
    }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            return tabNames[position];
//        }

    @Override
    public int getCount() {
        return 3;
    }


}
