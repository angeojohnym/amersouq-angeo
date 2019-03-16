package com.shopping.techxform.amersouq.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopping.techxform.amersouq.Models.ProfileModel;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.AlertActivity;
import com.shopping.techxform.amersouq.activities.CreateAdvertisement.CreateAdpage;
import com.shopping.techxform.amersouq.activities.FeedbackListActivity;
import com.shopping.techxform.amersouq.activities.ProductList.ProductListActivity;
import com.shopping.techxform.amersouq.activities.ProductList.ProductListActivityNew;

import java.util.List;

public class VendorProfileAdapter  extends RecyclerView.Adapter<VendorProfileAdapter.ViewHolder>  {

    private Context mContext;
    private List<ProfileModel> profileModels;

    public VendorProfileAdapter(Context mContext, List<ProfileModel> profileModels) {
        this.mContext = mContext;
        this.profileModels = profileModels;
    }

    @NonNull
    @Override
    public VendorProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_profile_list, parent, false);

        return new VendorProfileAdapter.ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull VendorProfileAdapter.ViewHolder holder, int position) {
        holder.ivItemImage.setBackground(mContext.getDrawable(profileModels.get(position).getImage()));
        holder.txtItemName.setText(profileModels.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return profileModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtItemName;
        public ImageView ivItemImage;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtItemName = itemView.findViewById(R.id.tv_txt);
            ivItemImage = itemView.findViewById(R.id.iv_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(getAdapterPosition()==0) {
                        Intent intent = new Intent(mContext, CreateAdpage.class);
                        intent.putExtra("flag","AddProduct");
                        mContext.startActivity(intent);
                    }else if(getAdapterPosition()==1) {
                        Intent intent = new Intent(mContext, ProductListActivityNew.class);
                        mContext.startActivity(intent);
                    }
                    else if(getAdapterPosition()==2) {
                        Intent intent = new Intent(mContext, FeedbackListActivity.class);
                        mContext.startActivity(intent);
                    }else if(getAdapterPosition()==3) {
                        Intent intent = new Intent(mContext, AlertActivity.class);
                        mContext.startActivity(intent);
                    }
                    else if(getAdapterPosition()==4){
                        Intent intent = new Intent(mContext, ProductListActivity.class);
                        mContext.startActivity(intent);
                    }
                    else if(getAdapterPosition()==6){
                        Intent intent = new Intent(mContext, CreateAdpage.class);
                        intent.putExtra("flag","AddAttribute");
                        mContext.startActivity(intent);
                    }
//                    else if(getAdapterPosition()==5){
//                        Intent intent = new Intent(mContext, BillingDetailsActivity.class);
//                        mContext.startActivity(intent);
//                    }
                }
            });

        }
        @Override
        public void onClick(View view) {

        }
    }

    public void notifyBtnClick(List<ProfileModel> profileModel){
        this.profileModels.clear();
        this.profileModels.addAll(profileModel);
        notifyDataSetChanged();
    }
}
