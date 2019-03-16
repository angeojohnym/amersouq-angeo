package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.fragments.amer_souq_collections.CategoryModel;
import com.shopping.techxform.amersouq.fragments.view_ads.ViewCategoriesSecondaryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 04-Dec-18.
 */


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryModel> namelist = new ArrayList<>();


    Context context;

    private int rowLayout;

    Activity activity;

    public CategoryAdapter(List<CategoryModel> namelist, Activity activity, int rowLayout, Context context) {
        this.namelist = namelist;
        this.activity = activity;
        this.rowLayout = rowLayout;
        this.context = context;


    }

    public List<CategoryModel> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.img_view.setImageResource(namelist.get(position).getImg_drawable());
        holder.cat_text_name.setText(namelist.get(position).getTitle_category());

        holder.img_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                bundle.putString("cat_id",namelist.get(position).getCat_id());

                ViewCategoriesSecondaryFragment secondaryFragment=new ViewCategoriesSecondaryFragment();
                secondaryFragment.setArguments(bundle);
                FragmentManager fragmentManager = activity.getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, secondaryFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
//        holder.img_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });



    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView img_view;
        TextView cat_text_name;
//        LinearLayout full_layout;


        public ViewHolder(View itemView) {
            super(itemView);

            img_view = (ImageView) itemView.findViewById(R.id.img_view);
            cat_text_name = (TextView) itemView.findViewById(R.id.cat_text_name);

//            full_layout = (LinearLayout) itemView.findViewById(R.id.full_layout);


        }

    }
}
