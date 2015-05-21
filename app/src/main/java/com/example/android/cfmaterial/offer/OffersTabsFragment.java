package com.example.android.cfmaterial.offer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.cfmaterial.NavigationDrawerFragment;
import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.TabsViewPagerAdapter;
import com.example.android.cfmaterial.navdrawer.NavDrawerAdapter;
import com.example.android.cfmaterial.navdrawer.NavDrawerItemClickedListener;
import com.example.android.cfmaterial.retailer.Retailer;
import com.example.android.cfmaterial.slidingtabs.SlidingTabLayout;

public class OffersTabsFragment extends NavigationDrawerFragment {

    private static final String RETAILER_KEY = "retailer_key";
    public static final String OFFER_CLIP_BROADCAST = "offerClipped";

    public static final String OFFER_HEADING = "offerHeading";
    public static final String OFFER_DESCRIPTION = "offerDescription";
    public static final String OFFER_DETAIL_DESCRIPTION = "offerDetailDesc";
    public static final String OFFER_TERMS = "offerTerms";
    public static final String OFFER_EXPIRATION = "offerExpiration";
    public static final String OFFER_ID = "offerId";

    private Retailer retailer;
    private NavDrawerItemClickedListener navDrawerItemClickedListener;

    public static OffersTabsFragment newInstance(Retailer retailer) {
        OffersTabsFragment fragment = new OffersTabsFragment();
        Bundle args = new Bundle();
        args.putParcelable(RETAILER_KEY, retailer);
        fragment.setArguments(args);
        return fragment;
    }

    public OffersTabsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            retailer = getArguments().getParcelable(RETAILER_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_offers_tabs, container, false);

        TabsViewPagerAdapter tabsViewPagerAdapter = new TabsViewPagerAdapter(getChildFragmentManager());

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.fragment_offers_tabs_view_pager);
        viewPager.setAdapter(tabsViewPagerAdapter);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.fragment_offers_tabs_sliding_tabs);
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

        return view;
    }

    @Override
    public void setupNavigationDrawer() {
        NavDrawerAdapter adapter = new NavDrawerAdapter(getActivity());
        adapter.addItem("Login", R.drawable.ic_action_accounts);
        adapter.addDivider();
        adapter.addHeader(retailer.getName());
        adapter.addItem("Offers", R.drawable.ic_action_view_as_list);
        adapter.addItem("Card", R.drawable.ic_action_labels);
        adapter.addItem("Stores", R.drawable.ic_action_map);
        adapter.addItem("History", R.drawable.ic_action_go_to_today);
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

        ImageView largeLogo = (ImageView) getActivity().findViewById(R.id.drawer_large_logo);
        ImageView smallLogo = (ImageView) getActivity().findViewById(R.id.drawer_small_logo);

        smallLogo.setVisibility(View.VISIBLE);
        smallLogo.setImageResource(R.drawable.cellfire_circle);
        largeLogo.setImageResource(retailer.getDrawableId());

        drawerListView.setOnItemClickListener(null);
        drawerListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                drawer.closeDrawers();
                switch (position) {
                    case 0:
                        navDrawerItemClickedListener.onNavDrawerItemClicked(NavDrawerItemClickedListener.NavDrawerItem.LOGIN);
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
            }
        });
        drawerListView.setAdapter(adapter);

        drawerListView. setItemChecked(3, true);
    }

    @Override
    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Offers");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            navDrawerItemClickedListener = (NavDrawerItemClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement listeners");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navDrawerItemClickedListener = null;
    }
}
