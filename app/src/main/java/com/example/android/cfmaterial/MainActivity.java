package com.example.android.cfmaterial;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.android.cfmaterial.navdrawer.NavDrawerItemClickedListener;
import com.example.android.cfmaterial.offer.OffersTabsFragment;
import com.example.android.cfmaterial.retailer.Retailer;
import com.example.android.cfmaterial.retailer.RetailersFragment;


public class MainActivity extends ActionBarActivity implements RetailersFragment.OnRetailerClickedListener, NavDrawerItemClickedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ListView drawerListView;

    // ---------- Lifecycle ---------- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerListView = (ListView) findViewById(R.id.drawer_listview);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle.syncState();

        if (savedInstanceState == null) {
            drawerListView.setItemChecked(3, true);
            RetailersFragment retailersFragment = RetailersFragment.newInstance(RetailersFragment.Mode.ALL);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_container, retailersFragment)
                    .commit();
        }
    }

    // ---------- Menu ---------- //

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    // ---------- RetailersFragment.OnRetailerClickedListener ---------- //

    @Override
    public void onRetailerClicked(Retailer retailer) {
        OffersTabsFragment offersTabsFragment = OffersTabsFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, offersTabsFragment)
                .addToBackStack(null)
                .commit();
    }

    // ---------- Aux ---------- //

    private void showRetailersFragment(RetailersFragment.Mode mode) {
        RetailersFragment retailersFragment = RetailersFragment.newInstance(mode);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, retailersFragment)
                .commit();
    }

    @Override
    public void onNavDrawerItemClicked(NavDrawerItem item) {
        switch (item) {
            case RET_ALL:
                showRetailersFragment(RetailersFragment.Mode.ALL);
                break;
            case RET_FAV:
                showRetailersFragment(RetailersFragment.Mode.FAVORITES);
                break;
            case RET_NEAR:
                showRetailersFragment(RetailersFragment.Mode.NEARBY);
                break;
        }
    }
}
