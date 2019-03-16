package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.All_Ads_Classified.AllAd_Classified;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.MyAdsOutput.AllAd;
import com.shopping.techxform.amersouq.activities.ViewAdvertisement.ViewFullAd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 12-Mar-19.
 */

public class AllAdsAdapter_Classifieds extends RecyclerView.Adapter<AllAdsAdapter_Classifieds.ViewHolder> {

    private List<AllAd_Classified> namelist = new ArrayList<>();
    private List<String> newlist = new ArrayList<>();
    HomeAdapter images_adapter;

    Context context;
    String type="";
    private int rowLayout;

    Activity activity;

    public AllAdsAdapter_Classifieds(List<AllAd_Classified> namelist, Activity activity, int rowLayout, Context context,String type) {
        this.namelist = namelist;
        this.activity = activity;
        this.rowLayout = rowLayout;
        this.context = context;
        this.type=type;


    }

    public List<AllAd_Classified> getStudentist() {
        return namelist;
    }

    @Override
    public AllAdsAdapter_Classifieds.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new AllAdsAdapter_Classifieds.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final AllAdsAdapter_Classifieds.ViewHolder holder, final int position) {


        final AllAd_Classified admodel=namelist.get(position);

        holder.ad_title.setText(admodel.getAdName());
        holder.ad_seller_name.setText(admodel.getUser());
        holder.price_td.setText("KWD"+admodel.getPrice());
        holder.offer_price_td.setText("KWD"+admodel.getOfferPrice());
        holder.date_td.setText(admodel.getAddedOn());
        holder.ad_title.setText(admodel.getAdName());

        String url_img=admodel.getImages().get(0);
        RequestOptions requestOption = new RequestOptions()
                .placeholder(R.drawable.placeholder);
        Glide.with(context).load(url_img)
                .apply(requestOption)
                .into(holder.full_img_ad);

        holder.full_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity,ViewFullAd.class);
                intent.putExtra("ad_type_id",admodel.getAdTypeId());
                intent.putExtra("ad_id",admodel.getAdId());
                intent.putExtra("type",type);

                activity.startActivity(intent);
            }
        });

        if(type.equals("my")){
            holder.seller_layout.setVisibility(View.GONE);
        }


        if(admodel.getAdTypeId().equals("1")){

            holder.adtype_td.setText("Classifieds");
            holder.ad_type_id.setBackgroundResource(R.drawable.bg_classified_effect);
        }
        else if(admodel.getAdTypeId().equals("2")){
            holder.adtype_td.setText("Fixed Price");
            holder.ad_type_id.setBackgroundResource(R.drawable.bg_fixed_price_effect);
        }
        else if(admodel.getAdTypeId().equals("3"))
        {
            holder.adtype_td.setText("Auction");
            holder.ad_type_id.setBackgroundResource(R.drawable.bg_auction_effect);
        }


//        holder.ad_type_id.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(activity,ViewFullAd.class);
//                intent.putExtra("ad_type_id",admodel.getAdTypeId());
//                intent.putExtra("ad_id",admodel.getAdId());
//                intent.putExtra("type",type);
//
//                activity.startActivity(intent);
//            }
//        });

//        holder.ad_title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(activity,ViewFullAd.class);
//                intent.putExtra("ad_type_id",admodel.getAdTypeId());
//                intent.putExtra("ad_id",admodel.getAdId());
//                intent.putExtra("type",type);
//
//                activity.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ad_type_id,seller_layout;
        RelativeLayout full_layout;
        ImageView full_img_ad;
        TextView price_td,offer_price_td,ad_title,ad_seller_name,offer_td,date_td,adtype_td;


        public ViewHolder(View itemView) {
            super(itemView);

            seller_layout=(LinearLayout)itemView.findViewById(R.id.seller_layout);
            full_layout = (RelativeLayout) itemView.findViewById(R.id.full_layout);
            full_img_ad = (ImageView) itemView.findViewById(R.id.full_img_ad);
            ad_type_id=(LinearLayout)itemView.findViewById(R.id.ad_type_id);

            price_td = (TextView) itemView.findViewById(R.id.price_td);
            offer_price_td = (TextView) itemView.findViewById(R.id.offer_price_td);
            ad_title = (TextView) itemView.findViewById(R.id.ad_title);
            ad_seller_name = (TextView) itemView.findViewById(R.id.ad_seller_name);
            offer_td = (TextView) itemView.findViewById(R.id.offer_td);

            date_td = (TextView) itemView.findViewById(R.id.date_td);
            adtype_td = (TextView) itemView.findViewById(R.id.adtype_td);


//            images_list_lay = (RecyclerView) itemView.findViewById(R.id.images_list_lay);



        }

    }
}
