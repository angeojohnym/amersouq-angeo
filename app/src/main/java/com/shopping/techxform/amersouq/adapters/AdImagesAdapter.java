package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.Utils.AdImagesModel;
import com.shopping.techxform.amersouq.fragments.ad_creation.ClassifiedFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 04-Jan-19.
 */

public class AdImagesAdapter extends RecyclerView.Adapter<AdImagesAdapter.ViewHolder> {

    private ArrayList<AdImagesModel> namelist = new ArrayList<>();
    private List<String> newlist = new ArrayList<>();
    HomeAdapter images_adapter;
    ClassifiedFragment fragment;
    Context context;

    Activity activity;

    public AdImagesAdapter(ArrayList<AdImagesModel> namelist, Activity activity, Context context,ClassifiedFragment fragment) {
        this.namelist = namelist;
        this.activity = activity;
        this.context = context;
        this.fragment = fragment;


    }

    public ArrayList<AdImagesModel> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_ad_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final AdImagesModel model=namelist.get(position);

//        holder.img_view.setText(namelist.get(position).getTitlename());

        Glide.with(activity)
                .load(model.getImage_uri())
                .into(holder.ad_imagevw);

        holder.close_imgvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment.removefromlist(model);


//                namelist.remove(model);
//                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView ad_imagevw,close_imgvw;


        public ViewHolder(View itemView) {
            super(itemView);

            ad_imagevw = (ImageView) itemView.findViewById(R.id.ad_imgvw);
            close_imgvw = (ImageView) itemView.findViewById(R.id.close_imgvw);



        }

    }
}
