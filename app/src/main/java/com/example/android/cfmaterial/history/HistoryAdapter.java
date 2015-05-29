package com.example.android.cfmaterial.history;

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
import com.example.android.cfmaterial.tabsfragments.History;
import com.example.android.cfmaterial.tabsfragments.Offers;

import java.util.List;

/**
 * Created by ravatapa on 5/28/2015.
 */
public class HistoryAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    public interface HistoryEventListener {
        public void OnOfferExpand(int groupPosition);
    }

    private HistoryEventListener historyEventListener;
    private Context context;
    private List<History> historyList;
    private Resources resources;

    public HistoryAdapter(HistoryEventListener historyEventListener, Context context, List<History> historyList, Resources resources) {
        this.historyEventListener = historyEventListener;
        this.context = context;
        this.historyList = historyList;
        this.resources = resources;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return historyList.get(groupPosition).getOffersList().size();
    }

    @Override
    public int getGroupCount() {
        return historyList.size();
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
            convertView = inflater.inflate(R.layout.history_row, null, false);

            viewHolder = new GroupViewHolder();
            viewHolder.date = (TextView) convertView.findViewById(R.id.history_date);
            viewHolder.savings = (TextView) convertView.findViewById(R.id.history_savings);
            viewHolder.expand = (Button) convertView.findViewById(R.id.history_expand);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }

        final History history = historyList.get(groupPosition);

        viewHolder.date.setText(history.getDate());
        viewHolder.savings.setText(history.getSavings());

        viewHolder.expand.setTag(groupPosition);
        viewHolder.expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int groupPosition = (int) v.getTag();
                historyEventListener.OnOfferExpand(groupPosition);
                final Button button = (Button) v;
                if (historyList.get(groupPosition).isExpanded()) {
                    historyList.get(groupPosition).setIsExpanded(false);
                    button.setBackgroundDrawable(resources.getDrawable(R.drawable.ic_action_expand));
                } else {
                    historyList.get(groupPosition).setIsExpanded(true);
                    button.setBackgroundDrawable(resources.getDrawable(R.drawable.ic_action_collapse));
                }
            }
        });

        return convertView;
    }


    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.history_details_row, null, false);

            viewHolder = new ChildViewHolder();
            viewHolder.heading = (TextView) convertView.findViewById(R.id.history_offer_heading);
            viewHolder.description = (TextView) convertView.findViewById(R.id.history_offer_description);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.history_offer_image);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }

        final Offers offers = historyList.get(groupPosition).getOffersList().get(childPosition);

        viewHolder.heading.setText(offers.getHeading());
        viewHolder.description.setText(offers.getDescription());

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
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class ChildViewHolder{
        ImageView imageView;
        TextView heading;
        TextView description;
    }

    private class GroupViewHolder{
        TextView date;
        TextView savings;
        Button expand;
    }

}
