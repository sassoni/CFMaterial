package com.example.android.cfmaterial.retailer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cfmaterial.R;

public class RetailersRecyclerAdapter extends RecyclerView.Adapter<RetailersRecyclerAdapter.ViewHolder> {

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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            titleView = (TextView) v.findViewById(R.id.retailers_gridview_item_title);
            imageView = (ImageView) v.findViewById(R.id.retailers_gridview_item_image);
        }
    }

    @Override
    public RetailersRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.retailers_gridview_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RetailersRecyclerAdapter.ViewHolder viewHolder, int position) {
        Retailer retailer = retailers[position];
        viewHolder.titleView.setText(retailer.getName());
        viewHolder.imageView.setImageResource(retailer.getDrawableId());
    }

    @Override
    public int getItemCount() {
        return retailers.length;
    }
}
