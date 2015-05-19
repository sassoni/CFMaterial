package com.example.android.cfmaterial;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.cfmaterial.navdrawer.NavDrawerAdapter;
import com.example.android.cfmaterial.offer.OffersTabsFragment;
import com.example.android.cfmaterial.retailer.Retailer;
import com.example.android.cfmaterial.retailer.RetailersFragment;


public class MainActivity extends ActionBarActivity implements RetailersFragment.OnRetailerClickedListener {

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

        drawerListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                drawer.closeDrawers();
                switch (position) {
                    case 3:
                        showRetailersFragment(RetailersFragment.Mode.ALL);
                        break;
                    case 4:
                        showRetailersFragment(RetailersFragment.Mode.FAVORITES);
                        break;
                    case 5:
                        showRetailersFragment(RetailersFragment.Mode.NEARBY);
                        break;
                    default:
                        break;
                }
            }

            // drawerListView. setItemChecked(position, true);
            //mDrawerLayout.closeDrawer(mDrawerList);
        });

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle.syncState();

        NavDrawerAdapter adapter = setupNavDrawerAdapter();
        drawerListView.setAdapter(adapter);

        if (savedInstanceState == null) {
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

    private NavDrawerAdapter setupNavDrawerAdapter() {
        NavDrawerAdapter adapter = new NavDrawerAdapter(this);
        adapter.addItem("Login", R.drawable.ic_action_accounts);
        adapter.addDivider();
        adapter.addHeader("Retailers");
        adapter.addItem("All", R.drawable.ic_action_view_as_grid);
        adapter.addItem("Favorites", R.drawable.ic_action_favorite);
        adapter.addItem("Nearby", R.drawable.ic_action_place);
        adapter.addDivider();
        adapter.addItem("Settings", R.drawable.ic_action_settings);
        adapter.addItem("Help", R.drawable.ic_action_help);
        adapter.addItem("About", R.drawable.ic_action_about);
        return adapter;
    }

    private void showRetailersFragment(RetailersFragment.Mode mode) {
        RetailersFragment retailersFragment = RetailersFragment.newInstance(mode);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_container, retailersFragment)
                .commit();
    }
}
