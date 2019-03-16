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
import com.bumptech.glide.request.RequestOptions;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData.BannerItem;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData.FeaturedAdsItem;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

    private List<BannerItem> namelist = new ArrayList<>();

    Context context;

    Activity activity;

    public BannerAdapter(List<BannerItem> namelist, Activity activity, Context context) {
        this.namelist = namelist;
        this.activity = activity;
        this.context = context;


    }

    public List<BannerItem> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_list_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


//        holder.img_view.setText(namelist.get(position).getTitlename());


//        holder.ad_title.setText(namelist.get(position).getTitle());
        RequestOptions requestOption = new RequestOptions()
                .placeholder(R.drawable.placeholder);

//                            Toast.makeText(getActivity(),responseBody.getClassified().getImages().get(0),Toast.LENGTH_SHORT).show();
        Glide.with(context).load(namelist.get(position).getImage())
                .apply(requestOption)
                .into(holder.img_view);

    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        RelativeLayout full_layout;
        ImageView img_view;
//        TextView ad_title;

        public ViewHolder(View itemView) {
            super(itemView);


//            ad_title=(TextView)itemView.findViewById(R.id.ad_title);
            img_view=(ImageView)itemView.findViewById(R.id.company_ads);
            full_layout=(RelativeLayout)itemView.findViewById(R.id.full_layout);



        }

    }

}
