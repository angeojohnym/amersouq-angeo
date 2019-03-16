package com.shopping.techxform.amersouq.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.techxform.amersouq.Models.Feedback;
import com.shopping.techxform.amersouq.Models.Product;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.listner.RecyclerClick;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Feedback> mDataList;
    private Context mContext;
    private RecyclerClick mRecyclerClick;

    public FeedbackAdapter(Context context, ArrayList<Feedback> productArrayList, RecyclerClick recyclerClick) {
        this.mContext=context;
        this.mDataList=productArrayList;
        this.mRecyclerClick=recyclerClick;
    }

    private class ItemNewArrivalsHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nameTv;
        private TextView discriptionTv;

        public ItemNewArrivalsHolder(View itemView) {
            super(itemView);
            imageView =  itemView.findViewById(R.id.image);
            nameTv =  itemView.findViewById(R.id.name_textview);
            discriptionTv =  itemView.findViewById(R.id.detail_textview);
        }
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_feedback, parent, false);
        return new ItemNewArrivalsHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, final int position) {
        final Feedback feedback = mDataList.get(position);
        if (feedback != null) {
            ItemNewArrivalsHolder holder = (ItemNewArrivalsHolder) rawHolder;
            Glide.with(mContext).load(feedback.getUserimage())
                    .into(holder.imageView);
            holder.nameTv.setText(feedback.getUser_name());
            holder.discriptionTv.setText(feedback.getBody());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mRecyclerClick.onClickItem(view, position);
                }
            });
        }
        }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}