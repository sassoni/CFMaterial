package com.example.android.cfmaterial.offer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.animatedexpandablelist.AnimatedExpandableListView;
import com.example.android.cfmaterial.tabsfragments.Offers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SavedOffersFragment extends Fragment implements SavedOffersAdapter.SavedOffersEventListener {

    public static final String PARAM1 = "param1";
    private List<Offers> savedOffers;

    private AnimatedExpandableListView expandableListView;
    private TextView savedOffersEmpty;
    private SavedOffersAdapter savedOffersAdapter;

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
        savedOffers = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_saved_offers, container, false);

        savedOffersEmpty = (TextView) view.findViewById(R.id.saved_offers_empty_message);

        loadOffersList(getString(R.string.offer_data_json));

        expandableListView = (AnimatedExpandableListView) view.findViewById(R.id.saved_offers_listview);
        savedOffersAdapter = new SavedOffersAdapter(getActivity(), savedOffers, this);
        expandableListView.setAdapter(savedOffersAdapter);
        expandableListView.setGroupIndicator(null);

        savedOffersListUICheck();
        //savedOffersAdapter.notifyDataSetChanged();
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

    public void loadOffersList(String jsonData) {
        savedOffers = new ArrayList<>();

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
                savedOffers.add(offers);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void savedOffersListUICheck(){
        if (savedOffers.size() == 0){
            savedOffersEmpty.setVisibility(View.VISIBLE);
            expandableListView.setVisibility(View.GONE);
        }else {
            savedOffersEmpty.setVisibility(View.GONE);
            expandableListView.setVisibility(View.VISIBLE);
        }
    }

}
