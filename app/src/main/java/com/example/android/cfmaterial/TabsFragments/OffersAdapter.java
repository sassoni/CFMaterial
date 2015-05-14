package com.example.android.cfmaterial.TabsFragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cfmaterial.R;

import java.util.List;

/**
 * Created by Avatapally on 12/30/14.
 */
public class OffersAdapter extends BaseAdapter {

    private List<Offers> offersList;
    private Context context;
    private Resources resources;

    public interface OffersGridEventListener {
        public void offerClipped();
    }

    private OffersGridEventListener offersGridEventListener;

    public OffersAdapter(Context context, List<Offers> offersList, OffersGridEventListener offersGridEventListener){
        this.offersList = offersList;
        this.context = context;
        resources = context.getResources();
        this.offersGridEventListener = offersGridEventListener;
    }

    @Override
    public int getCount() {
        return offersList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.offers_layout_list, null, false);

            viewHolder = new ViewHolder();
            viewHolder.description = (TextView) convertView.findViewById(R.id.offer_layout_offer_description);
            viewHolder.heading = (TextView) convertView.findViewById(R.id.offer_layout_offer_heading);
            viewHolder.expiration = (TextView) convertView.findViewById(R.id.offer_layout_offer_expiration);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.offer_layout_offer_image);
            viewHolder.offerClip = (Button) convertView.findViewById(R.id.offer_clip);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Offers offers = offersList.get(position);

        viewHolder.offerClip.setTag(position);
        setClippedButtonState(viewHolder.offerClip, offers.isClipped());

        viewHolder.offerClip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                Offers offersData = offersList.get(position);
                if (offersData.isClipped()) {
                    offersData.setClipped(false);
                    setClippedButtonState(viewHolder.offerClip, false);
                } else {
                    offersData.setClipped(true);
                    setClippedButtonState(viewHolder.offerClip, true);
                }
                offersGridEventListener.offerClipped();
            }
        });

        viewHolder.expiration.setText(offers.getExpiration());
        viewHolder.description.setText(offers.getDescription());
        viewHolder.heading.setText(offers.getHeading());

        Drawable drawable = null;
        switch (offers.getId()){
            case 0:
                drawable = context.getResources().getDrawable(R.drawable.i_allnaturaleggs);
                break;
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.i_breakstones);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.i_cheerios);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.i_chichi);
                break;
            case 4:
                drawable = context.getResources().getDrawable(R.drawable.i_chobani);
                break;
            case 5:
                drawable = context.getResources().getDrawable(R.drawable.i_floridasnaturals);
                break;
            case 6:
                drawable = context.getResources().getDrawable(R.drawable.i_generalmills);
                break;
            case 7:
                drawable = context.getResources().getDrawable(R.drawable.i_gerberorganicfood);
                break;
            case 8:
                drawable = context.getResources().getDrawable(R.drawable.i_gladeexpressions);
                break;
            case 9:
                drawable = context.getResources().getDrawable(R.drawable.i_highperformancedetergent);
                break;
            case 10:
                drawable = context.getResources().getDrawable(R.drawable.i_lobsterseafood);
                break;
            case 11:
                drawable = context.getResources().getDrawable(R.drawable.i_macandcheese);
                break;
            case 12:
                drawable = context.getResources().getDrawable(R.drawable.i_minutemaidlemonade);
                break;
            case 13:
                drawable = context.getResources().getDrawable(R.drawable.i_peperagefarms);
                break;
            case 14:
                drawable = context.getResources().getDrawable(R.drawable.i_perduechicken);
                break;
            case 15:
                drawable = context.getResources().getDrawable(R.drawable.i_puffs);
                break;
            case 16:
                drawable = context.getResources().getDrawable(R.drawable.i_scott);
                break;
            case 17:
                drawable = context.getResources().getDrawable(R.drawable.i_sscranberryjuice);
                break;
            case 18:
                drawable = context.getResources().getDrawable(R.drawable.i_specialk);
                break;
            default:
                drawable = context.getResources().getDrawable(R.drawable.i_scott);
                break;
        }
        viewHolder.imageView.setImageDrawable(drawable);

        return convertView;
    }

    private void setClippedButtonState(Button clippedButton, boolean isClipped) {
        if (isClipped) {
            clippedButton.setText(resources.getString(R.string.offer_clip_clipped));
            clippedButton.setTextColor(resources.getColor(R.color.offer_clipped_state));
            clippedButton.setCompoundDrawablesWithIntrinsicBounds(null, null, resources.getDrawable(R.drawable.i_saved), null);
        } else {
            clippedButton.setText(resources.getString(R.string.offer_clip_unclipped));
            clippedButton.setTextColor(resources.getColor(R.color.offer_unclipped_state));
            clippedButton.setCompoundDrawablesWithIntrinsicBounds(null, null, resources.getDrawable(R.drawable.i_addcoupon), null);
        }
    }

    private class ViewHolder{
        ImageView imageView;
        TextView heading;
        TextView description;
        TextView expiration;
        Button offerClip;
    }

}
