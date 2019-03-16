package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.AllCategory;
import com.shopping.techxform.amersouq.activities.ViewAdvertisement.ViewAllAds;
import com.shopping.techxform.amersouq.fragments.view_ads.ViewCategoriesSecondaryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 07-Jan-19.
 */


public class CategoriesSecondaryAdapter extends RecyclerView.Adapter<CategoriesSecondaryAdapter.ViewHolder> {

    HomeAdapter images_adapter;
    Context context;
    Activity activity;
    private List<AllCategory> namelist = new ArrayList<>();
    private List<String> newlist = new ArrayList<>();
    private int rowLayout;

    public CategoriesSecondaryAdapter(List<AllCategory> namelist, Activity activity, Context context) {
        this.namelist = namelist;
        this.activity = activity;
        this.context = context;


    }

    public List<AllCategory> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_plain_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.cat_td.setText(namelist.get(position).getCategoryName());
//        holder.img_view.setText(namelist.get(position).getTitlename());

        holder.cat_td.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (namelist.get(position).getIsChildren() == 1) {
                    Bundle bundle = new Bundle();
                    bundle.putString("cat_id", namelist.get(position).getCategoryId());

                    ViewCategoriesSecondaryFragment secondaryFragment = new ViewCategoriesSecondaryFragment();
                    secondaryFragment.setArguments(bundle);
                    FragmentManager fragmentManager = activity.getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, secondaryFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    Intent intent = new Intent(activity, ViewAllAds.class);
                    intent.putExtra("type", "all");
                    intent.putExtra("cat_id", namelist.get(position).getCategoryId());

                    activity.startActivity(intent);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView cat_td;


        public ViewHolder(View itemView) {
            super(itemView);

            cat_td = itemView.findViewById(R.id.cat_td);


        }

    }
}
