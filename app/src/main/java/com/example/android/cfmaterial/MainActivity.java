package com.example.android.cfmaterial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.cfmaterial.card.CardFragment;
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
            RetailersFragment retailersFragment = RetailersFragment.newInstance(RetailersFragment.Mode.NEARBY, true, 0);  // manual position?
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_container, retailersFragment)
                    .commit();
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
        OffersTabsFragment offersTabsFragment = OffersTabsFragment.newInstance(retailer, fragmentPosition);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, offersTabsFragment)
                .addToBackStack(null)
                .commit();
    }

    // ---------- Aux ---------- //

    @Override
    public void onNavDrawerItemClicked(int position, NavDrawerRow.Action item) {

        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        Log.i("MAIN", "count: " + backStackEntryCount);
        if (backStackEntryCount > 1) {
            getSupportFragmentManager().popBackStackImmediate();
        }

        /*
       DEFAULT
        HISTORY,
        SETTINGS,
        HELP,
        ABOUT

      */
        //Bundle bundle = new Bundle();
        //bundle.putInt(NAV_DRAWER_POSITION_KEY, position);

        switch (item) {
            case LOGIN:
                startLoginActivity(position);
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
                break;
            case HELP:
                Intent tutorialIntent = new Intent(this, TutorialActivity.class);
                tutorialIntent.putExtra(NAV_DRAWER_POSITION_KEY, position);
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

    private void startLoginActivity(int position) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(NAV_DRAWER_POSITION_KEY, position);
        startActivity(intent);
    }

    private void showRetailersFragment(RetailersFragment.Mode mode, int position) {
        RetailersFragment retailersFragment = RetailersFragment.newInstance(mode, false, position);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, retailersFragment)
                .commit();
    }

    private void showOffersFragment(int position) {
        OffersTabsFragment offersFragment = OffersTabsFragment.newInstance(currentRetailer, position);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, offersFragment)
                .addToBackStack(null)
                .commit();
    }

    private void showCardFrgament(CardFragment.Mode mode, int position) {
        CardFragment cardFragment = CardFragment.newInstance(mode, currentRetailer, position);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, cardFragment)
                .addToBackStack(null)
                .commit();
    }

    private void showStoresFragment(int position) {
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
}
