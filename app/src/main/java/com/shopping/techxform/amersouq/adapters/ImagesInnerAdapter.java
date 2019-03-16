package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shopping.techxform.amersouq.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 05-Jan-19.
 */



public class ImagesInnerAdapter extends RecyclerView.Adapter<ImagesInnerAdapter.ViewHolder> {

    private List<String> namelist = new ArrayList<>();
    private List<String> newlist = new ArrayList<>();
    HomeAdapter images_adapter;
    ImageView cover_img;
    Context context;

    Activity activity;

    public ImagesInnerAdapter(List<String> namelist, Activity activity, Context context,ImageView cover_img) {
        this.namelist = namelist;
        this.activity = activity;
        this.context = context;
        this.cover_img = cover_img;

    }

    public List<String> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


//        holder.img_view.setText(namelist.get(position).getTitlename());

        final RequestOptions requestOption = new RequestOptions()
                .placeholder(R.drawable.placeholder);

//                            Toast.makeText(getActivity(),responseBody.getClassified().getImages().get(0),Toast.LENGTH_SHORT).show();
        Glide.with(context).load(namelist.get(position))
                .apply(requestOption)
                .into(holder.image_vw);

        holder.image_vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(context).load(namelist.get(position))
                        .apply(requestOption)
                        .into(cover_img);
            }
        });


    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


      ImageView image_vw;


        public ViewHolder(View itemView) {
            super(itemView);

            image_vw = (ImageView) itemView.findViewById(R.id.img_view);




        }

    }
}
