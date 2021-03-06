package com.example.android.cfmaterial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.cfmaterial.card.CardFragment;
import com.example.android.cfmaterial.history.HistoryActivity;
import com.example.android.cfmaterial.navdrawer.NavDrawerItemClickedListener;
import com.example.android.cfmaterial.navdrawer.NavDrawerRow;
import com.example.android.cfmaterial.offer.OffersTabsFragment;
import com.example.android.cfmaterial.retailer.Retailer;
import com.example.android.cfmaterial.retailer.RetailersFragment;
import com.example.android.cfmaterial.settings.SettingsActivity;
import com.example.android.cfmaterial.stores.StoresFragment;
import com.example.android.cfmaterial.tutorial.TutorialActivity;


public class MainActivity extends AppCompatActivity implements RetailersFragment.OnRetailerClickedListener, NavDrawerItemClickedListener {

    public static final String NAV_DRAWER_POSITION_KEY = "nav_drawer_position_key";

    private DrawerLayout drawer;
    private Retailer currentRetailer;

    // ---------- Lifecycle ---------- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle.syncState();

        if (savedInstanceState == null) {
            showRetailersFragment(RetailersFragment.Mode.NEARBY, 0);

//            retailersFragment = RetailersFragment.newInstance(RetailersFragment.Mode.NEARBY, true, 0);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.activity_main_container, retailersFragment)
//                    //.addToBackStack(null)
//                    .commit();
        }
    }

    // ---------- Menu ---------- //

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    // ---------- RetailersFragment.OnRetailerClickedListener ---------- //

    @Override
    public void onRetailerClicked(Retailer retailer, int fragmentPosition) {
        currentRetailer = retailer;
        showOffersFragment(3/*fragmentPosition*/);
//        OffersTabsFragment offersTabsFragment = OffersTabsFragment.newInstance(retailer, fragmentPosition);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.activity_main_container, offersTabsFragment)
//                .addToBackStack(null)
//                .commit();
    }

    // ---------- Aux ---------- //

    @Override
    public void onNavDrawerItemClicked(int position, NavDrawerRow.Action item) {

//        // We wanna call that only when we show fragments, not activities
//        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
//        Log.i("MAIN", "count: " + backStackEntryCount);
//        if (backStackEntryCount > 1) {
//            getSupportFragmentManager().popBackStackImmediate();
//        }

        switch (item) {
            case LOGIN:
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case RETAILERS_SHOW_ALL:
                showRetailersFragment(RetailersFragment.Mode.ALL, position);
                break;
            case RETAILERS_SHOW_FAV:
                showRetailersFragment(RetailersFragment.Mode.FAVORITES, position);
                break;
            case RETAILERS_SHOW_NEAR:
                showRetailersFragment(RetailersFragment.Mode.NEARBY, position);
                break;
            case OFFERS:
                showOffersFragment(position);
                break;
            case ADD_CARD:
                showCardFrgament(CardFragment.Mode.EDIT, position);
                break;
            case MY_CARD:
                showCardFrgament(CardFragment.Mode.VIEW, position);
                break;
            case STORES:
                showStoresFragment(position);
                break;
            case HISTORY:
                Intent historyIntent = new Intent(this, HistoryActivity.class);
                startActivity(historyIntent);
                break;
            case HELP:
                Intent tutorialIntent = new Intent(this, TutorialActivity.class);
                startActivity(tutorialIntent);
                break;
            case ABOUT:
                break;
            case SETTINGS:
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;
        }

    }

    private void showRetailersFragment(RetailersFragment.Mode mode, int position) {
        popAllFromBackstack();
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);  // pop whole stack
        RetailersFragment retailersFragment = RetailersFragment.newInstance(mode, false, position);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, retailersFragment)
                .commit();
    }

    private void showOffersFragment(int position) {
        popAllFromBackstack();

        OffersTabsFragment offersFragment = OffersTabsFragment.newInstance(currentRetailer, position);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, offersFragment)
                .addToBackStack(null)
                .commit();
    }

    private void showCardFrgament(CardFragment.Mode mode, int position) {
        popAllFromBackstack();

        CardFragment cardFragment = CardFragment.newInstance(mode, currentRetailer, position);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, cardFragment)
                .addToBackStack(null)
                .commit();
    }

    private void showStoresFragment(int position) {
        popAllFromBackstack();

        StoresFragment storesFragment = StoresFragment.newInstance(currentRetailer, position);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, storesFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    private void popAllFromBackstack() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        Log.i("MAIN", "count: " + backStackEntryCount);
        if (backStackEntryCount > 1) {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }
}
