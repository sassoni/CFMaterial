package com.example.android.cfmaterial.history;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.animatedexpandablelist.AnimatedExpandableListView;
import com.example.android.cfmaterial.tabsfragments.History;
import com.example.android.cfmaterial.tabsfragments.Offers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravatapa on 5/27/2015.
 */
public class HistoryActivity extends AppCompatActivity implements HistoryAdapter.HistoryEventListener {

    private AnimatedExpandableListView historyListView;
    private List<History> historyList;
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.preference_activity_title));
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadHistoryList(getString(R.string.history_data_json));

        historyListView = (AnimatedExpandableListView) findViewById(R.id.history_list_view);
        historyListView.setGroupIndicator(null);
        historyAdapter = new HistoryAdapter(this, this, historyList, getResources());
        historyListView.setAdapter(historyAdapter);
    }

    private void loadHistoryList(String jsonString){
        try {

            JSONArray jsonArray = new JSONArray(jsonString);
            historyList = new ArrayList<>();
            for (int i =0 ; i < jsonArray.length(); i++){
                JSONObject parentJsonObject = jsonArray.getJSONObject(i);
                History history = new History();
                history.setDate(parentJsonObject.getString("date"));
                history.setSavings(parentJsonObject.getString("savings"));

                if (parentJsonObject.has("offers"))
                {
                    JSONArray offersArray = parentJsonObject.getJSONArray("offers");
                    List<Offers> offersList = new ArrayList<>();
                    for (int k = 0; k < offersArray.length(); k++) {
                        JSONObject childJsonObject = offersArray.getJSONObject(k);
                        Offers offers = new Offers();
                        offers.setHeading(childJsonObject.getString("heading"));
                        offers.setDescription(childJsonObject.getString("description"));
                        offers.setId(childJsonObject.getInt("offerId"));
                        offersList.add(offers);
                    }

                    history.setOffersList(offersList);
                }

                historyList.add(history);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnOfferExpand(int groupPosition) {
        if (historyListView.isGroupExpanded(groupPosition)){
            historyListView.collapseGroupWithAnimation(groupPosition);
        }else {
            historyListView.expandGroupWithAnimation(groupPosition);
        }
    }
}
