package com.example.android.cfmaterial.offer;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.cfmaterial.NavigationDrawerFragment;
import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.TabsViewPagerAdapter;
import com.example.android.cfmaterial.navdrawer.NavDrawerAdapter;
import com.example.android.cfmaterial.slidingtabs.SlidingTabLayout;

public class OffersTabsFragment extends NavigationDrawerFragment {

    private ViewPager viewPager;
    private TabsViewPagerAdapter tabsViewPagerAdapter;
    // private SavedOffersFragment savedOffersFragment;
    // private RetailerOffersFragment retailerOffersFragment;
   // private SlidingTabLayout slidingTabLayout;

//    private Toolbar toolbar;
//    private DrawerLayout drawerLayout;
//    private ActionBarDrawerToggle actionBarDrawerToggle;

/*    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";*/

/*    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;*/

    private OnFragmentInteractionListener mListener;

    public static OffersTabsFragment newInstance(/*String param1, String param2*/) {
        OffersTabsFragment fragment = new OffersTabsFragment();
/*        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    public OffersTabsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("OFFERSTABSFRAGMENT", "onCreate");
/*        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("OFFERSTABSFRAGMENT", "onCreateView");

        View view = inflater.inflate(R.layout.fragment_offers_tabs, container, false);

        tabsViewPagerAdapter = new TabsViewPagerAdapter(getChildFragmentManager());

        viewPager = (ViewPager) view.findViewById(R.id.fragment_offers_tabs_view_pager);
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
/*        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setupNavigationDrawer();
//    }

    @Override
    public void setupNavigationDrawer() {
        NavDrawerAdapter adapter = new NavDrawerAdapter(getActivity());
        adapter.addItem("Login", R.drawable.ic_action_accounts);
        adapter.addDivider();
        adapter.addHeader("A name");
        adapter.addItem("Offers", R.drawable.ic_action_view_as_list);
        adapter.addItem("Card", R.drawable.ic_action_labels);
        adapter.addItem("Stores", R.drawable.ic_action_make_available_offline);
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

        drawerListView.setOnItemClickListener(null);
        drawerListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                drawer.closeDrawers();
                switch (position) {
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

            // drawerListView. setItemChecked(position, true);
            //mDrawerLayout.closeDrawer(mDrawerList);
        });
        drawerListView.setAdapter(adapter);
    }

}
