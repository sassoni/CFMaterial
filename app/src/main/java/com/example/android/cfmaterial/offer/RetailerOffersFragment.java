package com.example.android.cfmaterial.offer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.animatedexpandablelist.AnimatedExpandableListView;
import com.example.android.cfmaterial.tabsfragments.Offers;
import com.example.android.cfmaterial.tabsfragments.OffersExpandableAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RetailerOffersFragment extends Fragment implements OffersExpandableAdapter.OffersGridEventListener, Handler.Callback {

    private static final String TAG = RetailerOffersFragment.class.getSimpleName();

    private static final String PARAM1 = "param1";
    private List<Offers> offersList;

    private AnimatedExpandableListView expandableListView;
    private OffersExpandableAdapter offersExpandableAdapter;
    private HashMap<Long, Integer> mItemIdTopMap = new HashMap<Long, Integer>();

    private static final int SWIPE_DURATION = 250;
    private static final int MOVE_DURATION = 250;

    private BackgroundContainer mBackgroundContainer;

    private TextView snackBarClipped;
    private LinearLayout snackBarError;
    private Button snackBarErrorRetry;

    //private ActionBar actionBar;
    /*private final int toolbarTop;
    private final int toolbarBottom;
    private final int toolbarL*/

    private static Handler handler;

    public interface RetailerOffersFragmentListener{
        public void OnOfferClip(Offers offers);
    }

    private RetailerOffersFragmentListener retailerOffersFragmentListener;

    public static RetailerOffersFragment newInstance(String param1) {
        RetailerOffersFragment retailerOffersFragment = new RetailerOffersFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM1, param1);
        retailerOffersFragment.setArguments(bundle);
        return retailerOffersFragment;
    }

    public RetailerOffersFragment() {
        /*actionBar = (Toolbar) getActivity().findViewById(R.id.actionBar);
        actionBar.*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (handler == null){
            handler = new Handler(this);
        }
    }
/*
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        retailerOffersFragmentListener = (RetailerOffersFragmentListener) getActivity();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retailer_offers, container, false);

        loadOffersList(getString(R.string.offer_data_json));

        mBackgroundContainer = (BackgroundContainer) view.findViewById(R.id.listViewBackground);
        expandableListView = (AnimatedExpandableListView) view.findViewById(R.id.offer_listview);
        offersExpandableAdapter = new OffersExpandableAdapter(getActivity(), offersList, RetailerOffersFragment.this);
        expandableListView.setAdapter(offersExpandableAdapter);
        expandableListView.setGroupIndicator(null);
        //expandableListView.setOnTouchListener(onTouchListener);

        snackBarClipped = (TextView) view.findViewById(R.id.snackbar_clip_success);
        snackBarError = (LinearLayout) view.findViewById(R.id.snackbar_clip_error);
        snackBarErrorRetry = (Button) view.findViewById(R.id.snackbar_clip_error_retry);
        snackBarErrorRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //actionBar = getActivity().getActionBar();

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
    public void offerClipped(final int groupPosition) {
        offersList.get(groupPosition).setIsClipping(true);
        offersExpandableAdapter.notifyDataSetChanged();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (groupPosition % 3 == 0) {
                    snackBarError.setVisibility(View.VISIBLE);
                    offersList.get(groupPosition).setIsClipping(false);
                    offersExpandableAdapter.notifyDataSetChanged();
                    snackBarError.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            snackBarError.setVisibility(View.GONE);
                        }
                    }, 3000);
                } else {
                    //retailerOffersFragmentListener.OnOfferClip(offersList.get(groupPosition));
                    animateCouponClipped(groupPosition);
                    snackBarClipped.setVisibility(View.VISIBLE);
                    snackBarClipped.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            snackBarClipped.setVisibility(View.GONE);
                        }
                    }, 3000);
                }
            }
        }, 2000);
        //animateCouponClipped(groupPosition);
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

    private void animateCouponClipped(int groupPosition){
        int firstVisiblePosition = expandableListView.getFirstVisiblePosition();
        int childPosition = groupPosition - firstVisiblePosition;
        View viewToRemove = expandableListView.getChildAt(childPosition);

        for (int i = 0; i < expandableListView.getChildCount(); ++i) {
            View child = expandableListView.getChildAt(i);
            if (child != viewToRemove) {
                int position = firstVisiblePosition + i;
                long itemId = offersExpandableAdapter.getGroupId(position);
                mItemIdTopMap.put(itemId, child.getTop());
            }
        }

        // Delete the item from the adapter
        int position = expandableListView.getPositionForView(viewToRemove);
        offersExpandableAdapter.remove(groupPosition);

        final ViewTreeObserver observer = expandableListView.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                observer.removeOnPreDrawListener(this);
                boolean firstAnimation = true;
                int firstVisiblePosition = expandableListView.getFirstVisiblePosition();
                for (int i = 0; i < expandableListView.getChildCount(); ++i) {
                    final View child = expandableListView.getChildAt(i);
                    int position = firstVisiblePosition + i;
                    long itemId = offersExpandableAdapter.getGroupId(position);
                    Integer startTop = mItemIdTopMap.get(itemId);
                    int top = child.getTop();
                    if (startTop != null) {
                        if (startTop != top) {
                            int delta = startTop - top;
                            child.setTranslationY(delta);
                            child.animate().setDuration(MOVE_DURATION).translationY(0);
                            if (firstAnimation) {
                                child.animate().withEndAction(new Runnable() {
                                    public void run() {
                                        mBackgroundContainer.hideBackground();
                                    }
                                });
                                firstAnimation = false;
                            }
                        }
                    } else {
                        // Animate new views along with the others. The catch is that they did not
                        // exist in the start state, so we must calculate their starting position
                        // based on neighboring views.
                        int childHeight = child.getHeight() + expandableListView.getDividerHeight();
                        startTop = top + (i > 0 ? childHeight : -childHeight);
                        int delta = startTop - top;
                        child.setTranslationY(delta);
                        child.animate().setDuration(MOVE_DURATION).translationY(0);
                        if (firstAnimation) {
                            child.animate().withEndAction(new Runnable() {
                                public void run() {
                                    mBackgroundContainer.hideBackground();
                                }
                            });
                            firstAnimation = false;
                        }
                    }
                }
                mItemIdTopMap.clear();
                return true;
            }
        });

    }

    /*private View.OnTouchListener onTouchListener = new View.OnTouchListener() {

        float mDownY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()){

                case MotionEvent.ACTION_DOWN:
                    Log.i(TAG, "Action Down");
                    mDownY = event.getY();
                    Log.i(TAG, "mDownY "+ mDownY);
                    break;

                case MotionEvent.ACTION_CANCEL:
                    Log.i(TAG, "Action Cancel");
                    break;

                case MotionEvent.ACTION_MOVE:
                    Log.i(TAG, "Action Move");
                    float deltaY = event.getY() - mDownY;
                    Log.i(TAG, "deltaY "+deltaY);

                    mDownY = event.getY();
                    Log.i(TAG, "mDownY " + mDownY);
                    hideToolBar(deltaY);
                    break;

                case MotionEvent.ACTION_UP:
                    Log.i(TAG, "Action Up");
                    mDownY = event.getY();
                    Log.i(TAG, "mDownY "+ mDownY);
                    break;

            }

            return false;
        }
    };*/

    private void resetToolBar(){

    }

    private void showToolBar(){

    }

    /*private void hideToolBar(float deltaY){
        if (deltaY < 0){
            deltaY = deltaY*-1;
            int newTop = actionBar.get - (int) deltaY;
            actionBar.setTop(newTop);
        }
    }*/

    @Override
    public boolean handleMessage(Message msg) {
        animateCouponClipped(msg.arg1);
        return true;
    }
}
