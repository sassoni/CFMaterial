package com.example.android.cfmaterial.offer;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.TabsViewPagerAdapter;
import com.example.android.cfmaterial.slidingtabs.SlidingTabLayout;

public class OffersTabsFragment extends Fragment {

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

}
