package com.example.yao.a94Asos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_DETAILS;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_NAME;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_SCALE;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_USER;

/**
 * Created by yaojunl on 2017/3/8.
 */

public class RecCampaignRecyclerViewAdapter extends RecyclerView.Adapter<RecCampaignRecyclerViewAdapter.ViewHolder> {

    private final List<VIPCampaign> mValues;
    private final List<Bitmap> mBitmapValues;

    public RecCampaignRecyclerViewAdapter(List<VIPCampaign> items,List<Bitmap> bitmapItems) {
        mValues = items;
        mBitmapValues = bitmapItems;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rec_campaign_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mBitmapItem = mBitmapValues.get(position);
        holder.mNameView.setText(holder.mItem.getName());
        holder.mScaleView.setText(holder.mItem.getScale()+"人");
        holder.mImageView.setImageBitmap(holder.mBitmapItem);

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
        public final ImageView mImageView;
        public final TextView mScaleView;
        public VIPCampaign mItem;
        public Bitmap mBitmapItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.rec_campaign_name);
            mImageView = (ImageView) view.findViewById(R.id.rec_campaign_img);
            mScaleView = (TextView) view.findViewById(R.id.rec_campaign_scale);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
