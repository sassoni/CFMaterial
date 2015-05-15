package com.example.android.cfmaterial.offer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.cfmaterial.R;

public class SavedOffersFragment extends Fragment {

    public static final String PARAM1 = "param1";

    public static SavedOffersFragment newInstance(String param1) {
        SavedOffersFragment savedOffersFragment = new SavedOffersFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM1, param1);
        savedOffersFragment.setArguments(bundle);
        return savedOffersFragment;
    }

    public SavedOffersFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_offers, container, false);
        return view;
    }
}
