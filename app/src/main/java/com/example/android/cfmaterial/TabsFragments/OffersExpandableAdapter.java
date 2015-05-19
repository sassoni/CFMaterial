package com.example.android.cfmaterial.tabsfragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.cfmaterial.R;
import com.example.android.cfmaterial.animatedexpandablelist.AnimatedExpandableListView;

import java.util.List;

/**
 * Created by ravatapa on 5/18/2015.
 */
public class OffersExpandableAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    public interface OffersGridEventListener {
        public void offerClipped(int groupPosition);
        public void onOfferExpand(int groupPosition);
    }

    private OffersGridEventListener offersGridEventListener;

    private Context context;
    private List<Offers> offersList;
    private Resources resources;

    public OffersExpandableAdapter(Context context, List<Offers> offersList, OffersGridEventListener offersGridEventListener) {
        this.context = context;
        this.offersList = offersList;
        resources = context.getResources();
        this.offersGridEventListener = offersGridEventListener;
    }

    @Override
    public int getGroupCount() {
        return offersList.size();
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.offers_layout_list, null, false);

            viewHolder = new GroupViewHolder();
            viewHolder.description = (TextView) convertView.findViewById(R.id.offer_layout_offer_description);
            viewHolder.heading = (TextView) convertView.findViewById(R.id.offer_layout_offer_heading);
            viewHolder.expiration = (TextView) convertView.findViewById(R.id.offer_layout_offer_expiration);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.offer_layout_offer_image);
            viewHolder.offerClip = (Button) convertView.findViewById(R.id.offer_clip);
            viewHolder.expand = (Button) convertView.findViewById(R.id.offer_expand);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }

        final Offers offers = offersList.get(groupPosition);

        viewHolder.expand.setTag(groupPosition);
        viewHolder.expand.setTag(R.id.offerExpand, true);
        viewHolder.expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Button button = (Button) v;

                offersGridEventListener.onOfferExpand((int) v.getTag());
                boolean aBoolean = (boolean) v.getTag(R.id.offerExpand);
                if (aBoolean == false){
                    v.setTag(R.id.offerExpand,  true);
                    button.setBackgroundDrawable(resources.getDrawable(R.drawable.ic_action_collapse));
                }else {
                    button.setBackgroundDrawable(resources.getDrawable(R.drawable.ic_action_expand));
                    v.setTag(R.id.offerExpand, false);
                }
            }
        });

        viewHolder.offerClip.setTag(groupPosition);
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
                offersGridEventListener.offerClipped((int) v.getTag());
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

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.offers_layout_child, null, false);

            viewHolder = new ChildViewHolder();
            viewHolder.textDetailDescription = (TextView) convertView.findViewById(R.id.offer_detail_description);
            viewHolder.textTerms = (TextView) convertView.findViewById(R.id.offer_terms);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }

        final Offers offers = offersList.get(groupPosition);

        viewHolder.textTerms.setText(offers.getTerms());
        viewHolder.textDetailDescription.setText(offers.getDetailDescription());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
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

    private class GroupViewHolder{
        ImageView imageView;
        TextView description;
        TextView heading;
        TextView expiration;
        Button offerClip;
        Button expand;
    }

    private class ChildViewHolder{
        TextView textDetailDescription;
        TextView textTerms;
    }

}
