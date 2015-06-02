package com.example.android.cfmaterial.navdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.cfmaterial.R;

public class NavDrawerRow {

    public enum Action {
        DEFAULT,
        LOGIN,
        RETAILERS_SHOW_ALL,
        RETAILERS_SHOW_FAV,
        RETAILERS_SHOW_NEAR,
        OFFERS,
        ADD_CARD,
        MY_CARD,
        STORES,
        HISTORY,
        SETTINGS,
        HELP,
        ABOUT
    }

    public enum Type {
        DIVIDER, HEADER, ITEM
    }

    private final Type type;
    private final String text;
    private final int icon;
    private final Action action;
    private final int position;

    public static class Builder {
        private final Type type;
        private final int position;

        private String text = "";
        private int icon = -1;
        private Action action = Action.DEFAULT;

        public Builder(Type type, int position) {
            this.type = type;
            this.position = position;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setIcon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder setAction(Action action) {
            this.action = action;
            return this;
        }

        public NavDrawerRow build() {
            return new NavDrawerRow(this);
        }
    }

    private NavDrawerRow(Builder builder) {
        this.type = builder.type;
        this.position = builder.position;
        this.text = builder.text;
        this.icon = builder.icon;
        this.action = builder.action;
    }

    View inflateView(Context context, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        switch (type) {
            case HEADER:
                view = inflater.inflate(R.layout.nav_drawer_row_header, parent, false);
                TextView headerTextView = (TextView) view.findViewById(R.id.nav_drawer_row_header_text);
                headerTextView.setText(text);
                view.setEnabled(false);
                view.setOnClickListener(null);
                break;
            case ITEM:
                view = inflater.inflate(R.layout.nav_drawer_row_item, parent, false);
                TextView textView = (TextView) view.findViewById(R.id.nav_drawer_row_item_text);
                textView.setText(text);
                textView.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
                break;
            default:
                // Fallthrough to divider
            case DIVIDER:
                view = inflater.inflate(R.layout.nav_drawer_row_divider, parent, false);
                break;
        }
        return view;
    }

    public Action getAction() {
        return action;
    }

    public int getPosition() {
        return position;
    }
}
