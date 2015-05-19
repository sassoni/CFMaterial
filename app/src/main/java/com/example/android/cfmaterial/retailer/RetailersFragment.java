package com.example.android.cfmaterial.retailer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.android.cfmaterial.NavigationDrawerFragment;
import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.navdrawer.NavDrawerAdapter;
import com.example.android.cfmaterial.navdrawer.NavDrawerItemClickedListener;

public class RetailersFragment extends NavigationDrawerFragment {

    NavDrawerItemClickedListener navDrawerItemClickedListener;
    public static final String MODE_KEY = "mode_key";

    public enum Mode {
        ALL, NEARBY, FAVORITES
    }

    private Mode mode;

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

    public interface OnRetailerClickedListener {
        public void onRetailerClicked(Retailer retailer);
    }

    private OnRetailerClickedListener listener;

    public static RetailersFragment newInstance(Mode mode) {
        RetailersFragment fragment = new RetailersFragment();
        Bundle args = new Bundle();
        args.putSerializable(MODE_KEY, mode);
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retailers, container, false);

        RetailersAdapter adapter = new RetailersAdapter(getActivity(), retailers);

        GridView gridView = (GridView) view.findViewById(R.id.fragment_retailers_gridview);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    listener.onRetailerClicked(retailers[position]);
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnRetailerClickedListener) activity;
            navDrawerItemClickedListener = (NavDrawerItemClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnRetailerClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
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

        ListView drawerListView = (ListView) getActivity().findViewById(R.id.drawer_listview);
        final DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

        drawerListView.setOnItemClickListener(null);
        drawerListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
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
                        break;
                    case 9:
                        // show about
                    default:
                        break;
                }
            }

            // drawerListView. setItemChecked(position, true);
            //mDrawerLayout.closeDrawer(mDrawerList);
        });
        drawerListView.setAdapter(adapter);
    }
}
