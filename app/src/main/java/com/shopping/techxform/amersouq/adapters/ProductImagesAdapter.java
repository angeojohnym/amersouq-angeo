package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.Utils.AdImagesModel;
import com.shopping.techxform.amersouq.activities.AddProductNew;
import com.shopping.techxform.amersouq.fragments.ad_creation.ClassifiedFragment;

import java.util.ArrayList;
import java.util.List;



public class ProductImagesAdapter extends RecyclerView.Adapter<ProductImagesAdapter.ViewHolder> {

    private ArrayList<AdImagesModel> namelist = new ArrayList<>();
    Context context;

    Activity activity;

    public ProductImagesAdapter(ArrayList<AdImagesModel> namelist, Activity activity, Context context) {
        this.namelist = namelist;
        this.activity = activity;
        this.context = context;


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


        Glide.with(activity)
                .load(model.getImage_uri())
                .into(holder.ad_imagevw);

        holder.close_imgvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(context instanceof AddProductNew){
                    ((AddProductNew)context).removefromlist(model);
                }


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
