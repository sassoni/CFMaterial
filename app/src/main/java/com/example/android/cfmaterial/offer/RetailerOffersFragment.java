package com.example.android.cfmaterial.offer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.tabsfragments.Offers;
import com.example.android.cfmaterial.tabsfragments.OffersAdapter;
import com.example.android.cfmaterial.tabsfragments.OffersGridAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RetailerOffersFragment extends Fragment implements OffersAdapter.OffersGridEventListener {

    private static final String PARAM1 = "param1";
    private GridView gridView;
    private OffersGridAdapter offersGridAdapter;
    private List<Offers> offersList;

    private ListView listView;
    private OffersAdapter offersAdapter;

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

        /*gridView = (GridView) view.findViewById(R.id.offer_fragment_gridview);
        offersGridAdapter = new OffersGridAdapter(getActivity(), offersList, RetailerOffers.this);
        gridView.setAdapter(offersGridAdapter);*/

        listView = (ListView) view.findViewById(R.id.offer_listview);
        offersAdapter = new OffersAdapter(getActivity(), offersList, RetailerOffersFragment.this);
        listView.setAdapter(offersAdapter);

        return view;
    }

    @Override
    public void offerClipped() {

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
                offersList.add(offers);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
