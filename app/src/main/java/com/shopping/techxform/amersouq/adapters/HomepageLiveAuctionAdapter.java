package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData.LiveAuctionItem;
import com.shopping.techxform.amersouq.activities.ViewAdvertisement.ViewFullAd;

import java.util.ArrayList;
import java.util.List;


public class HomepageLiveAuctionAdapter extends RecyclerView.Adapter<HomepageLiveAuctionAdapter.ViewHolder> {

    private List<LiveAuctionItem> namelist = new ArrayList<>();
    HomeAdapter images_adapter;

    Context context;
    private int rowLayout;

    Activity activity;

    public HomepageLiveAuctionAdapter(List<LiveAuctionItem> namelist, Activity activity, Context context) {
        this.namelist = namelist;
        this.activity = activity;
        this.rowLayout = rowLayout;
        this.context = context;


    }

    public List<LiveAuctionItem> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_homepage_auction, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


//        holder.img_view.setText(namelist.get(position).getTitlename());

    holder.ad_title.setText(namelist.get(position).getTitle());

        holder.full_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, ViewFullAd.class);
                intent.putExtra("ad_type_id","3");
                intent.putExtra("ad_id",namelist.get(position).getLiveAdId());
                intent.putExtra("type","all");
                activity.startActivity(intent);
            }
        });
        holder.time_td.setText(namelist.get(position).getFromDate().substring(11,19));
        holder.date_td.setText(namelist.get(position).getFromDate().substring(0,10));
        holder.current_bid.setText(namelist.get(position).getCurrentBid());
        holder.cost_item.setText(namelist.get(position).getOfferPrice());
        holder.location_td.setText(namelist.get(position).getState());

    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView ad_title,location_td,cost_item,current_bid,date_td,week_day,time_td;
        RelativeLayout full_layout;


        public ViewHolder(View itemView) {
            super(itemView);

            ad_title=(TextView)itemView.findViewById(R.id.ad_title);
            full_layout=(RelativeLayout)itemView.findViewById(R.id.full_layout);

            cost_item=(TextView)itemView.findViewById(R.id.cost_item);
            location_td=(TextView) itemView.findViewById(R.id.location_td);
            current_bid=(TextView)itemView.findViewById(R.id.current_bid);
            date_td=(TextView) itemView.findViewById(R.id.date_td);
            week_day=(TextView) itemView.findViewById(R.id.week_day);
            time_td=(TextView) itemView.findViewById(R.id.time_td);


        }

    }
}
