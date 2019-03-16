package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryForsaleData.ForsaleCategory;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData.ForsaleCategoryItem;
import com.shopping.techxform.amersouq.activities.Category.SelectCategoryPage;
import com.shopping.techxform.amersouq.activities.ViewAdvertisement.ViewAllAds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 05-Jan-19.
 */


public class CategoryHomeIconAdapter extends RecyclerView.Adapter<CategoryHomeIconAdapter.ViewHolder> {

    HomeAdapter images_adapter;
    Context context;
    Activity activity;
    private List<ForsaleCategoryItem> namelist = new ArrayList<>();
    private List<String> newlist = new ArrayList<>();
    private int rowLayout;

    public CategoryHomeIconAdapter(List<ForsaleCategoryItem> namelist, Activity activity, Context context) {
        this.namelist = namelist;
        this.activity = activity;
        this.context = context;
    }

    public List<ForsaleCategoryItem> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


//        holder.img_view.setText(namelist.get(position).getTitlename());

        holder.cat_name.setText(namelist.get(position).getCategoryName());

        if(!namelist.get(position).getCategoryId().equals("0")) {

            RequestOptions requestOption = new RequestOptions()
                    .placeholder(R.drawable.placeholder);

//                            Toast.makeText(getActivity(),responseBody.getClassified().getImages().get(0),Toast.LENGTH_SHORT).show();
            Glide.with(context).load(namelist.get(position).getImage())
                    .apply(requestOption)
                    .into(holder.cat_icon);
//        holder.cat_icon.setImageDrawable(context.getResources().getDrawable(namelist.get(position).getImage()));

            holder.full_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, ViewAllAds.class);
                    intent.putExtra("type", "all");
                    intent.putExtra("cat_id", namelist.get(position).getCategoryId());

                    activity.startActivity(intent);
                }
            });
        }
        else {
            Glide.with(context).load(R.drawable.see_more_icon)
                    .into(holder.cat_icon);
//        holder.cat_icon.setImageDrawable(context.getResources().getDrawable(namelist.get(position).getImage()));

            holder.full_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, SelectCategoryPage.class);
//                    intent.putExtra("type", "all");
                    activity.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout full_layout;
        ImageView cat_icon;
        TextView cat_name;

        public ViewHolder(View itemView) {
            super(itemView);

            cat_icon=(ImageView)itemView.findViewById(R.id.img_view);
            cat_name=(TextView)itemView.findViewById(R.id.cat_name);
            full_layout=(LinearLayout) itemView.findViewById(R.id.full_layout);


        }

    }
}
