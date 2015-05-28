package com.example.android.cfmaterial.retailer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cfmaterial.R;

import java.util.List;

public class RetailersAdapter extends ArrayAdapter<Retailer> {

    private Context context;
    private List<Retailer> retailers;

    public RetailersAdapter(Context context, List<Retailer> retailers) {
        super(context, 0, retailers);
        this.context = context;
        this.retailers = retailers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.retailers_gridview_tile, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.retailers_gridview_tile_image);
        imageView.setImageResource(retailers.get(position).getDrawableId());

        TextView textView = (TextView) view.findViewById(R.id.retailers_gridview_tile_text);
        textView.setText(retailers.get(position).getName());

        return view;
    }

    @Override
    public int getCount() {
        return retailers.size();
    }
}
