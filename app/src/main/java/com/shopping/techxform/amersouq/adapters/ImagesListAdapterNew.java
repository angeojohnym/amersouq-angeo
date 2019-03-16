package com.shopping.techxform.amersouq.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.techxform.amersouq.Models.Product;
import com.shopping.techxform.amersouq.R;

import java.util.ArrayList;
import java.util.List;

public class ImagesListAdapterNew extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mDataList;
    private int mRowIndex = -1;
    private Context mContext;

    public ImagesListAdapterNew(Context context) {
        this.mContext=context;
    }

    public void setData(List<String> data) {
        if (mDataList != data) {
            mDataList = data;
            notifyDataSetChanged();
        }
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    private class ItemNewArrivalsHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;


        public ItemNewArrivalsHolder(View itemView) {
            super(itemView);
            imageView =  itemView.findViewById(R.id.img_view);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_image, parent, false);
        return new ItemNewArrivalsHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, final int position) {
        final String product = mDataList.get(position);
        if (product != null) {
                    ItemNewArrivalsHolder holder = (ItemNewArrivalsHolder) rawHolder;
            Glide.with(mContext).load(product)
                    .into(holder.imageView);

            }
        }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}