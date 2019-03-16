package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData.FeaturedAdsItem;
import com.shopping.techxform.amersouq.activities.ViewAdvertisement.ViewFullAd;

import java.util.ArrayList;
import java.util.List;


public class HomeFeaturedAdAdapter extends RecyclerView.Adapter<HomeFeaturedAdAdapter.ViewHolder> {

    private List<FeaturedAdsItem> namelist = new ArrayList<>();

    Context context;

    Activity activity;

    public HomeFeaturedAdAdapter(List<FeaturedAdsItem> namelist, Activity activity, Context context) {
        this.namelist = namelist;
        this.activity = activity;
        this.context = context;


    }

    public List<FeaturedAdsItem> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_ad_list_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


//        holder.img_view.setText(namelist.get(position).getTitlename());


        holder.ad_title.setText(namelist.get(position).getTitle());
        RequestOptions requestOption = new RequestOptions()
                .placeholder(R.drawable.placeholder);

//                            Toast.makeText(getActivity(),responseBody.getClassified().getImages().get(0),Toast.LENGTH_SHORT).show();
        Glide.with(context).load(namelist.get(position).getImage())
                .apply(requestOption)
                .into(holder.img_view);

        holder.full_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, ViewFullAd.class);
                intent.putExtra("ad_type_id",namelist.get(position).getAdTypeId());
                intent.putExtra("ad_id",namelist.get(position).getAdId());
                intent.putExtra("type","all");
                activity.startActivity(intent);
            }
        });

        holder.ad_desc.setText(namelist.get(position).getShortDescription());

        holder.price_td.setText(namelist.get(position).getPrice());
        holder.offer_price_td.setText(namelist.get(position).getOfferPrice());
//        holder.price_td.setPaintFlags(holder.price_td.getPaintFlags() Paint.UNDERLINE_TEXT_FLAG);
        holder.price_td.setPaintFlags(holder.price_td.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        LinearLayout full_layout;
        ImageView img_view;
        TextView ad_title,ad_desc,price_td,offer_price_td;

        public ViewHolder(View itemView) {
            super(itemView);
            ad_desc=(TextView)itemView.findViewById(R.id.ad_desc);
            price_td=(TextView)itemView.findViewById(R.id.price_td);
            offer_price_td=(TextView)itemView.findViewById(R.id.offer_price_td);

            ad_title=(TextView)itemView.findViewById(R.id.ad_title);
            img_view=(ImageView)itemView.findViewById(R.id.img_view);
            full_layout=(LinearLayout)itemView.findViewById(R.id.full_layout);



        }

    }
}
