package com.example.android.cfmaterial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.cfmaterial.navdrawer.NavDrawerItemClickedListener;
import com.example.android.cfmaterial.navdrawer.NavDrawerRow;
import com.example.android.cfmaterial.offer.OffersTabsFragment;
import com.example.android.cfmaterial.retailer.Retailer;
import com.example.android.cfmaterial.retailer.RetailersFragment;
import com.example.android.cfmaterial.stores.StoresFragment;
import com.example.android.cfmaterial.tutorial.TutorialActivity;


public class MainActivity extends AppCompatActivity implements RetailersFragment.OnRetailerClickedListener, NavDrawerItemClickedListener {

    // ---------- Lifecycle ---------- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);
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
//        if (!mNavigationDrawerFragment.isDrawerOpen()) {
//            // Only show items in the action bar relevant to this screen
//            // if the drawerLayout is not showing. Otherwise, let the drawerLayout
//            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.main, menu);
//            restoreActionBar();
//            return true;
//        }
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ---------- RetailersFragment.OnRetailerClickedListener ---------- //

    @Override
    public void onRetailerClicked(Retailer retailer, int fragmentPosition) {
        OffersTabsFragment offersTabsFragment = OffersTabsFragment.newInstance(retailer, fragmentPosition);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, offersTabsFragment)
                .addToBackStack(null)
                .commit();
    }

    // ---------- Aux ---------- //

    private void showRetailersFragment(RetailersFragment.Mode mode, int position) {
        RetailersFragment retailersFragment = RetailersFragment.newInstance(mode, false, position);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, retailersFragment)
                .commit();
    }

    @Override
    public void onNavDrawerItemClicked(int position, NavDrawerRow.Action item) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        switch (item) {
            case LOGIN:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
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
            case HELP:
                Intent tutorialIntent = new Intent(this, TutorialActivity.class);
                tutorialIntent.putExtra("position", position);
                startActivity(tutorialIntent);
                break;
            case STORES:
                StoresFragment storesFragment = StoresFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_container, storesFragment)
                        .addToBackStack(null)
                        .commit();
                break;
        }

    }
}
