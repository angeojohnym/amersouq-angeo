package com.shopping.techxform.amersouq.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.shopping.techxform.amersouq.Models.MyOrdersList;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.ProductDetails.ProductDetailsActivity;
import com.shopping.techxform.amersouq.activities.ProductList.ProductListActivity;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder>  {

    private Context mContext;
    private List<MyOrdersList> myOrdersLists;

    public ProductListAdapter(Context mContext, List<MyOrdersList> myOrdersLists) {
        this.mContext = mContext;
        this.myOrdersLists = myOrdersLists;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_my_order_item, parent, false);

        return new ProductListAdapter.ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        holder.ivItemImage.setBackground(mContext.getDrawable(myOrdersLists.get(position).getImgage()));
        holder.txtItemName.setText(myOrdersLists.get(position).getName());
        holder.txtStatus.setText(myOrdersLists.get(position).getStatus());
        if(myOrdersLists.get(position).getRating() > 0){
            holder.ratingLayout.setVisibility(View.VISIBLE);
            holder.rate.setRating(myOrdersLists.get(position).getRating());
        }else{
            holder.ratingLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return myOrdersLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtItemName, txtStatus;
        public ImageView ivItemImage;
        public RatingBar rate;
        public ConstraintLayout ratingLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            rate = itemView.findViewById(R.id.rate);
            rate.setNumStars(5);
            ivItemImage = itemView.findViewById(R.id.iv_item);
            txtItemName = itemView.findViewById(R.id.tv_item_name);
            txtStatus = itemView.findViewById(R.id.tv_status);
            ratingLayout = itemView.findViewById(R.id.rating_layout);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ProductDetailsActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }
        @Override
        public void onClick(View view) {

        }
    }

    public void notifyBtnClick(List<MyOrdersList> myOrdersLists){
        this.myOrdersLists.clear();
        this.myOrdersLists.addAll(myOrdersLists);
        notifyDataSetChanged();
    }
}
