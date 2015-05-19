package com.example.android.cfmaterial.offer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.animatedexpandablelist.AnimatedExpandableListView;
import com.example.android.cfmaterial.tabsfragments.Offers;
import com.example.android.cfmaterial.tabsfragments.OffersExpandableAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RetailerOffersFragment extends Fragment implements OffersExpandableAdapter.OffersGridEventListener {

    private static final String PARAM1 = "param1";
    private List<Offers> offersList;

    private AnimatedExpandableListView expandableListView;
    private OffersExpandableAdapter offersExpandableAdapter;

    public static RetailerOffersFragment newInstance(String param1) {
        RetailerOffersFragment retailerOffersFragment = new RetailerOffersFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM1, param1);
        retailerOffersFragment.setArguments(bundle);
        return retailerOffersFragment;
    }

    public RetailerOffersFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retailer_offers, container, false);

        loadOffersList(getString(R.string.offer_data_json));

        expandableListView = (AnimatedExpandableListView) view.findViewById(R.id.offer_listview);
        offersExpandableAdapter = new OffersExpandableAdapter(getActivity(), offersList, RetailerOffersFragment.this);
        expandableListView.setAdapter(offersExpandableAdapter);
        expandableListView.setGroupIndicator(null);
        return view;
    }

    @Override
    public void onOfferExpand(int groupPosition) {
        if (expandableListView.isGroupExpanded(groupPosition)){
            expandableListView.collapseGroupWithAnimation(groupPosition);
        }else {
            expandableListView.expandGroupWithAnimation(groupPosition);
        }
    }

    @Override
    public void offerClipped(int groupPosition) {

    }

    public void loadOffersList(String jsonData) {
        offersList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Offers offers = new Offers();
                offers.setDescription(jsonObject.getString("description"));
                offers.setExpiration(jsonObject.getString("expiration"));
                offers.setHeading(jsonObject.getString("heading"));
                offers.setId(jsonObject.getInt("offerId"));
                offers.setDetailDescription(jsonObject.getString("detailDescription"));
                offers.setTerms(jsonObject.getString("terms"));
                offersList.add(offers);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
