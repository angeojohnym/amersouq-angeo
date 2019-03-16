package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.ViewProduct.ViewProductActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 09-Dec-18.
 */



public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<String> namelist = new ArrayList<>();
    private List<String> newlist = new ArrayList<>();
    HomeAdapter images_adapter;

    Context context;
    private int rowLayout;

    Activity activity;

    public ProductsAdapter(List<String> namelist, Activity activity, int rowLayout, Context context) {
        this.namelist = namelist;
        this.activity = activity;
        this.rowLayout = rowLayout;
        this.context = context;


    }

    public List<String> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


//        holder.img_view.setText(namelist.get(position).getTitlename());
        newlist.clear();

        for(int i=0;i<10;i++){

            newlist.add("fgh");
        }

        images_adapter=new HomeAdapter(newlist,activity, R.layout.image_item,context,0);
        holder.images_list_lay.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        holder.images_list_lay.setItemAnimator(new DefaultItemAnimator());
        holder.images_list_lay.setNestedScrollingEnabled(false);
        holder.images_list_lay.setAdapter(images_adapter);

        holder.full_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(activity, ViewProductActivity.class);
                activity.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        RelativeLayout full_layout;
        RecyclerView images_list_lay;


        public ViewHolder(View itemView) {
            super(itemView);


            full_layout = (RelativeLayout) itemView.findViewById(R.id.full_layout);
            images_list_lay = (RecyclerView) itemView.findViewById(R.id.images_list_lay);



        }

    }
}
