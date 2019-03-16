package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.shopping.techxform.amersouq.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 15-Dec-18.
 */



public class AlertsAdapter extends RecyclerView.Adapter<AlertsAdapter.ViewHolder> {

    private List<String> namelist = new ArrayList<>();
    private List<String> newlist = new ArrayList<>();
    HomeAdapter images_adapter;

    Context context;
    private int rowLayout;

    Activity activity;

    public AlertsAdapter(List<String> namelist, Activity activity, int rowLayout, Context context) {
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




    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        RelativeLayout full_layout;


        public ViewHolder(View itemView) {
            super(itemView);





        }

    }
}
