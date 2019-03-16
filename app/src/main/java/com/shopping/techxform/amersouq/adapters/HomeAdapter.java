package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.listner.RecyclerClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 08-Jul-18.
 */


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    int k;
    Activity activity;
    private List<String> namelist = new ArrayList<>();
    private RecyclerClick mRecyclerClick;
    private int rowLayout;

    public HomeAdapter(List<String> namelist, Activity activity, int rowLayout, Context context, int k) {
        this.namelist = namelist;
        this.activity = activity;
        this.rowLayout = rowLayout;
        this.context = context;
        this.k = k;


    }

    public HomeAdapter(List<String> namelist, Activity activity, int rowLayout, Context context, int k, RecyclerClick recyclerClick) {
        this.namelist = namelist;
        this.activity = activity;
        this.rowLayout = rowLayout;
        this.context = context;
        this.k = k;
        this.mRecyclerClick = recyclerClick;


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


//        holder.full_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mRecyclerClick != null)
//                    mRecyclerClick.onClickItem(view, position);
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
        RelativeLayout full_layout;


        public ViewHolder(View itemView) {
            super(itemView);

            img_view = (ImageView) itemView.findViewById(R.id.img_view);
            full_layout = (RelativeLayout) itemView.findViewById(R.id.full_layout);


        }

    }
}
