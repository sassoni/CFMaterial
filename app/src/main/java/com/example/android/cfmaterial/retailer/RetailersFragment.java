package com.example.android.cfmaterial.retailer;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.cfmaterial.NavigationDrawerFragment;
import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.navdrawer.NavDrawerAdapter;
import com.example.android.cfmaterial.navdrawer.NavDrawerItemClickedListener;

public class RetailersFragment extends NavigationDrawerFragment {

    private static final String MODE_KEY = "mode_key";
    private static final String SHOW_LOADING_KEY = "show_loading_key";

    // Mode: All, favorites or nearby
    public enum Mode {
        ALL, NEARBY, FAVORITES
    }

    public interface OnRetailerClickedListener {
        public void onRetailerClicked(Retailer retailer);
    }

    private OnRetailerClickedListener retailerClickedListener;
    private NavDrawerItemClickedListener navDrawerItemClickedListener;

    private Mode mode = Mode.ALL;
    private boolean showLoading = false;

    private GridView gridView;
    private LinearLayout progressLayout;
    private ImageView progressBar;
    private TextView progressText;
    private AnimationDrawable loadingAnimation;

    private Retailer[] allRetailers = new Retailer[]{
            new Retailer("A&P", R.drawable.i_aandp),
            new Retailer("Family Express", R.drawable.i_familyexpress),
            new Retailer("Giant Carlisle", R.drawable.i_gc),
            new Retailer("Giant Landover", R.drawable.i_gl),
            new Retailer("Harris Teeter", R.drawable.i_harristeeter),
            new Retailer("Kroger", R.drawable.i_kroger),
            new Retailer("Marsh", R.drawable.i_marsh),
            new Retailer("Martin's", R.drawable.i_martins),
            new Retailer("Safeway", R.drawable.i_safeway),
            new Retailer("ShopRite", R.drawable.i_shoprite),
            new Retailer("Stop&Shop", R.drawable.i_stopandshop),
            new Retailer("Walmart", R.drawable.i_walmart),
            new Retailer("Weis", R.drawable.i_weis)
    };

    private Retailer[] nearbyRetailers = new Retailer[]{
            new Retailer("Giant Landover", R.drawable.i_gl),
            new Retailer("Harris Teeter", R.drawable.i_harristeeter),
            new Retailer("Marsh", R.drawable.i_marsh),
            new Retailer("Martin's", R.drawable.i_martins),
    };

    private Retailer[] favoriteRetailers = new Retailer[]{
            new Retailer("Kroger", R.drawable.i_kroger),
            new Retailer("Walmart", R.drawable.i_walmart),
    };

    Retailer[] retailers = allRetailers;

    public static RetailersFragment newInstance(Mode mode, boolean showLoading) {
        RetailersFragment fragment = new RetailersFragment();
        Bundle args = new Bundle();
        args.putSerializable(MODE_KEY, mode);
        args.putBoolean(SHOW_LOADING_KEY, showLoading);
        fragment.setArguments(args);
        return fragment;
    }

    public RetailersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mode = (Mode) getArguments().getSerializable(MODE_KEY);
            switch (mode) {
                case ALL:
                    retailers = allRetailers;
                    break;
                case NEARBY:
                    retailers = nearbyRetailers;
                    break;
                case FAVORITES:
                    retailers = favoriteRetailers;
                    break;
            }
            showLoading = getArguments().getBoolean(SHOW_LOADING_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retailers, container, false);

        RetailersAdapter adapter = new RetailersAdapter(getActivity(), retailers);

        gridView = (GridView) view.findViewById(R.id.fragment_retailers_gridview);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (retailerClickedListener != null) {
                    retailerClickedListener.onRetailerClicked(retailers[position]);
                }
            }
        });

        progressLayout = (LinearLayout) view.findViewById(R.id.fragment_retailers_progress_layout);
        progressBar = (ImageView) view.findViewById(R.id.fragment_retailers_progress_bar);
        progressText = (TextView) view.findViewById(R.id.fragment_retailers_progress_text);

        loadingAnimation = (AnimationDrawable) progressBar.getDrawable();
        loadingAnimation.setCallback(progressBar);

        progressLayout.setVisibility(View.GONE);
        gridView.setVisibility(View.VISIBLE);
        if (showLoading) {
            new LoadingAsyncTask().execute();
            showLoading = false;
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            retailerClickedListener = (OnRetailerClickedListener) activity;
            navDrawerItemClickedListener = (NavDrawerItemClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement listeners");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        retailerClickedListener = null;
        navDrawerItemClickedListener = null;
    }

    @Override
    public void setupNavigationDrawer() {
        final NavDrawerAdapter adapter = new NavDrawerAdapter(getActivity());
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

        final ListView drawerListView = (ListView) getActivity().findViewById(R.id.drawer_listview);
        final DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        ImageView largeLogo = (ImageView) getActivity().findViewById(R.id.drawer_large_logo);
        ImageView smallLogo = (ImageView) getActivity().findViewById(R.id.drawer_small_logo);

        smallLogo.setVisibility(View.GONE);
        largeLogo.setImageResource(R.drawable.cellfire_circle);

        drawerListView.setOnItemClickListener(null);
        drawerListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawer.closeDrawers();
                switch (position) {
                    case 0:
                        // login

                        break;
                    case 3:
                        // show all retailers
                        navDrawerItemClickedListener.onNavDrawerItemClicked(NavDrawerItemClickedListener.NavDrawerItem.RET_ALL);
                        break;
                    case 4:
                        // show favorites
                        navDrawerItemClickedListener.onNavDrawerItemClicked(NavDrawerItemClickedListener.NavDrawerItem.RET_FAV);
                        break;
                    case 5:
                        // show nearby
                        navDrawerItemClickedListener.onNavDrawerItemClicked(NavDrawerItemClickedListener.NavDrawerItem.RET_NEAR);
                        break;
                    case 7:
                        // show settings
                        break;
                    case 8:
                        // show help
                        navDrawerItemClickedListener.onNavDrawerItemClicked(NavDrawerItemClickedListener.NavDrawerItem.HELP);
                        break;
                    case 9:
                        // show about
                    default:
                        break;
                }
            }
        });
        drawerListView.setAdapter(adapter);

        switch (mode) {
            case ALL:
                drawerListView. setItemChecked(3, true);
                break;
            case FAVORITES:
                drawerListView. setItemChecked(4, true);
                break;
            case NEARBY:
                drawerListView. setItemChecked(5, true);
                break;
        }
    }

    @Override
    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        switch (mode) {
            case ALL:
                toolbar.setTitle("All retailers");
                break;
            case FAVORITES:
                toolbar.setTitle("Favorite retailers");
                break;
            case NEARBY:
                toolbar.setTitle("Nearby retailers");
                break;
        }
    }

    public class LoadingAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressLayout.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
            loadingAnimation.setVisible(true, true);
            loadingAnimation.start();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
                publishProgress();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
            progressText.setText("Loading...");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressLayout.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
            loadingAnimation.stop();
            setupToolbar();
        }
    }
}
