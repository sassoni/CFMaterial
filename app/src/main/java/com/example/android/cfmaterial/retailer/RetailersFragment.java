package com.example.android.cfmaterial.retailer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.cfmaterial.R;

public class RetailersFragment extends Fragment {

    private Retailer[] retailers = new Retailer[]{
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

    public interface OnRetailerClickedListener {
        public void onRetailerClicked(Retailer retailer);
    }

    private OnRetailerClickedListener listener;

    public static RetailersFragment newInstance() {
        return new RetailersFragment();
    }

    public RetailersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retailers, container, false);
        Log.i("RETAILERSFRAGMENT", "onCreateView");
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
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnRetailerClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
