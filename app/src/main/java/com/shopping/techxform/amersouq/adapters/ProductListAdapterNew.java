package com.shopping.techxform.amersouq.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.shopping.techxform.amersouq.Models.All_products;
import com.shopping.techxform.amersouq.Models.Product;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.listner.RecyclerClick;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductListAdapterNew extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<All_products> mDataList;
    private int mRowIndex = -1;
    private Context mContext;
    public static DecimalFormat formater = new DecimalFormat("0.00");
    private RecyclerClick mRecyclerClick;
    public ProductListAdapterNew(Context context, ArrayList<All_products> productArrayList,RecyclerClick recyclerClick) {
        this.mContext=context;
        this.mDataList=productArrayList;
        this.mRecyclerClick=recyclerClick;
    }

    public void setData(List<All_products> data) {
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
        private TextView nameTv;
        private TextView disCountTv;
        private TextView disCountedPriceTv;
        private ImagesListAdapterNew horizontalAdapter;
        private TextView actualPrice;
        private TextView ratingStar;
        private TextView ratingReview;
        private RecyclerView imageListRv;

        public ItemNewArrivalsHolder(View itemView) {
            super(itemView);
            imageView =  itemView.findViewById(R.id.product_imageview);
            imageListRv =  itemView.findViewById(R.id.product_images_recyclerview);
            nameTv =  itemView.findViewById(R.id.name_textview);
            disCountedPriceTv =  itemView.findViewById(R.id.discounted_price_textview);
            actualPrice =  itemView.findViewById(R.id.actual_price_textview);
            disCountTv =  itemView.findViewById(R.id.percent_textview);
            ratingStar =  itemView.findViewById(R.id.rating_star_textview);
            ratingReview =  itemView.findViewById(R.id.rating_and_review_textview);
            imageListRv.setNestedScrollingEnabled(false);
            imageListRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            horizontalAdapter = new ImagesListAdapterNew(mContext);
            imageListRv.setAdapter(horizontalAdapter);
        }
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_product_list, parent, false);
        return new ItemNewArrivalsHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, final int position) {
        final All_products product = mDataList.get(position);
        if (product != null) {
                    ItemNewArrivalsHolder holder = (ItemNewArrivalsHolder) rawHolder;
                    if (product.getImages().length>0){
                        Glide.with(mContext).load(product.getImages()[0])
                                .into(holder.imageView);
                    }

            holder.nameTv.setText(String.format("%s\n%s", product.getProduct_name(), product.getShort_desc()));
            holder.actualPrice.setText(String.format("%s%s", mContext.getString(R.string.Rs), product.getPrice()));
            holder.actualPrice.setPaintFlags(holder.actualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.disCountTv.setText(String.format("%s%% off", "10"));
//            holder.ratingStar.setText(product.getRatings());
//            holder.ratingReview.setText(product.getReviews());
            holder.disCountedPriceTv.setText(String.format("%s%s", mContext.getString(R.string.Rs), product.getOffer_price()));
            holder.horizontalAdapter.setData(Arrays.asList(product.getImages())); // List of Strings
            holder.horizontalAdapter.setRowIndex(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mRecyclerClick.onClickItem(view,position);
                }
            });
            }

        }

    private double getDiscountedPrice(double actualPrice, double discount) {
        return actualPrice-actualPrice*discount/100;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}