package com.example.android.cfmaterial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.android.cfmaterial.TabsFragments.RetailerOffers;
import com.example.android.cfmaterial.TabsFragments.SavedOffers;
import com.example.android.cfmaterial.slidingtabs.SlidingTabLayout;


public class RetailerActivity extends ActionBarActivity {

    private ViewPager viewPager;
    private TabsViewPagerAdapter tabsViewPagerAdapter;

    private SavedOffers savedOffers;
    private RetailerOffers retailerOffers;

    private SlidingTabLayout slidingTabLayout;

    private Toolbar toolbar;

    private static final String TAG = RetailerActivity.class.getSimpleName();

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retailer_view);

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        savedOffers = SavedOffers.newInstance("one");
        retailerOffers = RetailerOffers.newInstance("two");

        String[] tabNames = new String[]{"RETAILER OFFERS","SAVED OFFERS"};
        Fragment[] fragments = new Fragment[2];
        fragments[0] = retailerOffers;
        fragments[1] = savedOffers;

        tabsViewPagerAdapter = new TabsViewPagerAdapter(getSupportFragmentManager(), 2,tabNames,fragments);
        viewPager.setAdapter(tabsViewPagerAdapter);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        slidingTabLayout.setViewPager(viewPager);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }

            @Override
            public int getDividerColor(int position) {
                return Color.WHITE;
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };

        actionBarDrawerToggle.syncState();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
