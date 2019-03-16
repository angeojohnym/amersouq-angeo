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
import com.shopping.techxform.amersouq.Models.Billing;
import com.shopping.techxform.amersouq.Models.Product;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.listner.RecyclerClick;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BillingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Billing> mDataList;
    private int mRowIndex = -1;
    private Context mContext;
    public static DecimalFormat formater = new DecimalFormat("0.00");
    private RecyclerClick mRecyclerClick;
    public BillingAdapter(Context context, ArrayList<Billing> productArrayList, RecyclerClick recyclerClick) {
        this.mContext=context;
        this.mDataList=productArrayList;
        this.mRecyclerClick=recyclerClick;
    }

    private class ItemNewArrivalsHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nameTv;
        private TextView cardDetails;
        private TextView expires;

        public ItemNewArrivalsHolder(View itemView) {
            super(itemView);
            imageView =  itemView.findViewById(R.id.edit_imageview);
            nameTv =  itemView.findViewById(R.id.name_textview);
            cardDetails =  itemView.findViewById(R.id.card_details_textview);
            expires =  itemView.findViewById(R.id.expires_textview);
        }
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_billing_adapter, parent, false);
        return new ItemNewArrivalsHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, final int position) {
        final Billing billing = mDataList.get(position);
        if (billing != null) {
            ItemNewArrivalsHolder holder = (ItemNewArrivalsHolder) rawHolder;
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}