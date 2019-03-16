package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.AllCategory;
import com.shopping.techxform.amersouq.fragments.ad_creation.PostCategoryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 02-Jan-19.
 */


public class CategoryDynamicAdapter extends RecyclerView.Adapter<CategoryDynamicAdapter.ViewHolder> {

    PostCategoryFragment fragment;
    Context context;
    int flag;
    int row_no=-1;

    LinearLayout linearLayout;
    Activity activity;
    private List<AllCategory> namelist = new ArrayList<>();

    public CategoryDynamicAdapter(List<AllCategory> namelist, Activity activity, Context context, int flag, LinearLayout linearLayout, PostCategoryFragment fragment) {
        this.namelist = namelist;
        this.activity = activity;
        this.context = context;
        this.flag = flag;
        this.linearLayout = linearLayout;
        this.fragment=fragment;

    }

    public List<AllCategory> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_dynamic_child, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.flag_dynamic.setText(Integer.toString(flag));
        holder.cat_text_name.setText(namelist.get(position).getCategoryName());
        holder.cat_text_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                fragment.remove_extra(flag);

                row_no=position;
                fragment.set_new_catlist(namelist.get(position).getCategoryId(),namelist.get(position).getCategoryName(),1,Integer.parseInt(holder.flag_dynamic.getText().toString()));
                fragment.all_features(namelist.get(position));
                notifyDataSetChanged();


            }
        });


        if(row_no==position){
            holder.full_layout.setBackgroundResource(R.drawable.bg_orange_grey_stroke);
            holder.cat_text_name.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            holder.full_layout.setBackgroundResource(R.drawable.bg_grey_stroke);
            holder.cat_text_name.setTextColor(Color.parseColor("#000000"));
        }

    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView cat_text_name,flag_dynamic;
        RelativeLayout full_layout;


        public ViewHolder(View itemView) {
            super(itemView);

            cat_text_name = (TextView) itemView.findViewById(R.id.cat_name_txt);
            flag_dynamic = (TextView) itemView.findViewById(R.id.flag_dynamic);
            full_layout=(RelativeLayout)itemView.findViewById(R.id.full_layout);

        }

    }
}
