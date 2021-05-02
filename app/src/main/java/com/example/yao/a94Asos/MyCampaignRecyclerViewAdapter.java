package com.example.yao.a94Asos;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yaojunl on 2017/3/8.
 */

public class MyCampaignRecyclerViewAdapter extends RecyclerView.Adapter<MyCampaignRecyclerViewAdapter.ViewHolder> {

    public static final String ITEM_NAME = "item_name";
    public static final String ITEM_SCALE = "item_scale";
    public static final String ITEM_DETAILS = "item_details";
    public static final String ITEM_TARGET = "item_target";
    public static final String ITEM_USER = "item_user";
    public static final String ITEM_OBJECTID = "item_objectid";

    private final List<Campaign> mValues;

    public MyCampaignRecyclerViewAdapter(List<Campaign> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_campaign_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(holder.mItem.getName());
        holder.mScaleView.setText(holder.mItem.getScale()+"人");
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(ITEM_NAME, holder.mItem.getName());
                intent.putExtra(ITEM_SCALE, holder.mItem.getScale());
                intent.putExtra(ITEM_DETAILS, holder.mItem.getDetails());
                intent.putExtra(ITEM_USER, holder.mItem.getUser());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mScaleView;
        public Campaign mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.cam_name);
            mScaleView = (TextView) view.findViewById(R.id.cam_scale);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mScaleView.getText() + "'";
        }
    }
}
