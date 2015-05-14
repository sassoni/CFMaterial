package com.example.android.cfmaterial.TabsFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.cfmaterial.R;


/**
 * Created by ravatapa on 5/6/2015.
 */
public class SavedOffers extends Fragment {

    public static final String PARAM1 = "param1";

    public static SavedOffers newInstance(String param1){
        SavedOffers savedOffers = new SavedOffers();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM1, param1);
        savedOffers.setArguments(bundle);
        return savedOffers;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saved_offers, container, false);
        return view;
    }
}
