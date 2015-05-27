package com.example.android.cfmaterial.stores;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.cfmaterial.MainActivity;
import com.example.android.cfmaterial.NavigationDrawerFragment;
import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.retailer.Retailer;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

public class StoresFragment extends NavigationDrawerFragment implements OnMapReadyCallback {

    private static final String RETAILER_KEY = "retailer_key";

    private OnFragmentInteractionListener mListener;

    private SupportMapFragment mapFragment;
    private ListView storesListView;
    private FloatingActionButton fab;

    private String[] stores = new String[]{"Store 2", "Store 3", "Store 4", "Store 5", "Store 6"};
    private ArrayList<String> list;
    private Retailer retailer;

    boolean listHidden = true;

    public static StoresFragment newInstance(Retailer retailer, int position) {
        StoresFragment fragment = new StoresFragment();
        Bundle args = new Bundle();
        args.putParcelable(RETAILER_KEY, retailer);
        args.putInt(MainActivity.NAV_DRAWER_POSITION_KEY, position);
        fragment.setArguments(args);
        return fragment;
    }

    public StoresFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null) {
            retailer = getArguments().getParcelable(RETAILER_KEY);
            positionInNavDrawer = getArguments().getInt(MainActivity.NAV_DRAWER_POSITION_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stores, container, false);
        storesListView = (ListView) view.findViewById(R.id.stores_list);
        list = new ArrayList<String>();
        for (int i = 0; i < stores.length; ++i) {
            list.add(stores[i]);
        }
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        storesListView.setAdapter(itemsAdapter);
        storesListView.setVisibility(View.INVISIBLE);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listHidden) {
                    int cx = (fab.getLeft() + fab.getRight()) / 2;
                    int cy = (fab.getTop() + fab.getBottom()) / 2;

                    int finalRadius = Math.max(storesListView.getWidth(), storesListView.getHeight());

                    Animator anim = ViewAnimationUtils.createCircularReveal(storesListView, cx, cy, 0, finalRadius);

                    storesListView.setVisibility(View.VISIBLE);
                    anim.start();
                    listHidden = false;
                } else {
                    int cx = (fab.getLeft() + fab.getRight()) / 2;
                    int cy = (fab.getTop() + fab.getBottom()) / 2;

                    int initialRadius = storesListView.getWidth();

                    Animator anim = ViewAnimationUtils.createCircularReveal(storesListView, cx, cy, initialRadius, 0);

                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            storesListView.setVisibility(View.INVISIBLE);
                        }
                    });

                    anim.start();
                    listHidden = true;
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void setupNavigationDrawer() {
        ListView drawerListView = (ListView) getActivity().findViewById(R.id.drawer_listview);
        drawerListView.setItemChecked(positionInNavDrawer, true);
    }

    @Override
    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Stores");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_scan).setVisible(false);
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_discard).setVisible(false);
        menu.findItem(R.id.action_edit).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO Or getFragmmnetManager()???????????
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng office = new LatLng(42.351875, -71.048417);
        LatLng aquarium = new LatLng(42.360020, -71.051116);
        LatLng chinatown = new LatLng(42.352562, -71.062719);
        LatLng bac = new LatLng(42.339692, -71.043085);

        googleMap.setMyLocationEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(office, 13));

        googleMap.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(office));
        googleMap.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(aquarium));
        googleMap.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(chinatown));
        googleMap.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(bac));
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
