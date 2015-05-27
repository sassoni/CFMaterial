package com.example.android.cfmaterial.card;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.cfmaterial.MainActivity;
import com.example.android.cfmaterial.NavigationDrawerFragment;
import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.navdrawer.NavDrawerAdapter;
import com.example.android.cfmaterial.navdrawer.NavDrawerItemClickedListener;
import com.example.android.cfmaterial.retailer.Retailer;
import com.melnykov.fab.FloatingActionButton;

public class CardFragment extends NavigationDrawerFragment {

    private static final String MODE_KEY = "mode_key";
    private static final String RETAILER_KEY = "retailer_key";

    private NavDrawerItemClickedListener navDrawerItemClickedListener;
    private Retailer retailer;

    private EditText numberText;
    private EditText notesText;
    private FloatingActionButton fab;

    public enum Mode {
        EDIT, VIEW
    }

    private Mode mode;

    public static CardFragment newInstance(Mode mode, Retailer retailer, int position) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putSerializable(MODE_KEY, mode);
        args.putParcelable(RETAILER_KEY, retailer);
        args.putInt(MainActivity.NAV_DRAWER_POSITION_KEY, position);
        fragment.setArguments(args);
        return fragment;
    }

    public CardFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mode = (Mode) getArguments().getSerializable(MODE_KEY);
            positionInNavDrawer = getArguments().getInt(MainActivity.NAV_DRAWER_POSITION_KEY);
            retailer = getArguments().getParcelable(RETAILER_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        numberText = (EditText) view.findViewById(R.id.fragment_card_number_text);
        notesText = (EditText) view.findViewById(R.id.fragment_card_notes_text);
        fab = (FloatingActionButton) view.findViewById(R.id.fragment_card_fab);

        switch (mode) {
            case VIEW:
                disableEditing();
                break;
            case EDIT:
                enableEditing();
                break;
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            navDrawerItemClickedListener = (NavDrawerItemClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NavDrawerItemClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navDrawerItemClickedListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem search = menu.findItem(R.id.action_search);
        MenuItem scan = menu.findItem(R.id.action_scan);
        MenuItem save = menu.findItem(R.id.action_save);
        MenuItem discard = menu.findItem(R.id.action_discard);
        MenuItem edit = menu.findItem(R.id.action_edit);

        search.setVisible(false);
        scan.setVisible(false);
        switch (mode) {
            case VIEW:
                save.setVisible(false);
                discard.setVisible(false);

                edit.setVisible(true);
                edit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        enableEditing();
                        return true;
                    }
                });
                break;
            case EDIT:
                save.setVisible(true);
                save.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getActivity().getApplicationContext(), "Card saved",
                                Toast.LENGTH_LONG).show();
                        getActivity().getSupportFragmentManager().popBackStack();
                        return true;
                    }
                });

                discard.setVisible(true);
                discard.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getActivity().getApplicationContext(), "Changes discarded",
                                Toast.LENGTH_LONG).show();
                        getActivity().getSupportFragmentManager().popBackStack();
                        return true;
                    }
                });

                edit.setVisible(false);
                break;
        }
    }

    @Override
    public void setupNavigationDrawer() {
        ImageView largeLogo = (ImageView) getActivity().findViewById(R.id.drawer_large_logo);
        ImageView smallLogo = (ImageView) getActivity().findViewById(R.id.drawer_small_logo);
        smallLogo.setVisibility(View.VISIBLE);
        smallLogo.setImageResource(R.drawable.cellfire_circle);
        largeLogo.setImageResource(retailer.getDrawableId());

        final NavDrawerAdapter adapter = new NavDrawerAdapter(getActivity(), navDrawerItemClickedListener);
        adapter.populate(retailer);

        ListView drawerListView = (ListView) getActivity().findViewById(R.id.drawer_listview);
        final DrawerLayout drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

        drawerListView.setAdapter(adapter);
        drawerListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerLayout.closeDrawers();
                navDrawerItemClickedListener.onNavDrawerItemClicked(position, adapter.getItem(position).getAction());
            }
        });
        drawerListView.setItemChecked(positionInNavDrawer, true);
    }

    @Override
    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        String title = retailer.hasCard() ? "My Card" : "Add Card";
        toolbar.setTitle(title);
    }

    private void enableEditing() {
        mode = Mode.EDIT;
        getActivity().supportInvalidateOptionsMenu();
        numberText.setEnabled(true);
        notesText.setEnabled(true);
        fab.setVisibility(View.VISIBLE);
        fab.startAnimation(createAppearingAnimation());
    }

    private void disableEditing() {
        mode = Mode.VIEW;
        getActivity().supportInvalidateOptionsMenu();
        numberText.setEnabled(false);
        notesText.setEnabled(false);
        fab.setVisibility(View.INVISIBLE);
    }

    private Animation createAppearingAnimation() {
        Animation animation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fab.clearAnimation();
                fab.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        animation.setDuration(500);
        animation.setFillAfter(true);
        return animation;
    }

}
