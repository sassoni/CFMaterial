package com.example.android.cfmaterial.retailer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cfmaterial.R;

public class RetailersAdapter extends ArrayAdapter<Retailer> {

    private Context context;

    private Retailer[] retailers = new Retailer[]{
            new Retailer("A&P", R.drawable.i_aandp),
            new Retailer("Family Express", R.drawable.i_familyexpress),
            new Retailer("Giant Carlisle", R.drawable.i_gc),
            new Retailer("Giant Landover", R.drawable.i_gl),
            new Retailer("Harris Teeter", R.drawable.i_harristeeter),
            new Retailer("Kroger", R.drawable.i_kroger),
            new Retailer("Marsh", R.drawable.i_marsh),
            new Retailer("Martin's", R.drawable.i_martins),
            new Retailer("Safeway", R.drawable.i_safeway),
            new Retailer("ShopRite", R.drawable.i_shoprite),
            new Retailer("Stop&Shop", R.drawable.i_stopandshop),
            new Retailer("Walmart", R.drawable.i_walmart),
            new Retailer("Weis", R.drawable.i_weis)
    };

    public RetailersAdapter(Context context) {
        super(context, 0);
        this.context = context;
    }

//    public void addData() {
////        for (Retailer retailer : retailers) {
////            add(retailer);
////        }
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("RETAILERSADAPTER", "getView");
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.retailers_gridview_tile, parent, false);
       // TextView textView = (TextView) view.findViewById(R.id.retailers_gridview_tile_title);
        ImageView imageView = (ImageView) view.findViewById(R.id.retailers_gridview_tile_image);
        //textView.setText(retailers[position].getName());
        imageView.setImageResource(retailers[position].getDrawableId());
        return view;
    }

    @Override
    public int getCount() {
        return retailers.length;
    }
}
