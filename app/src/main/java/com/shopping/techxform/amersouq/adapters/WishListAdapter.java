package com.shopping.techxform.amersouq.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.DeleteWishData.DeleteWishModel;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.Disable_Ad.OptionAddOutput_Disable;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.WishOutput.WishAd;
import com.shopping.techxform.amersouq.Utils.AdImagesModel;
import com.shopping.techxform.amersouq.activities.NewProfile.NewProfileActivity;
import com.shopping.techxform.amersouq.activities.ViewWishList;
import com.shopping.techxform.amersouq.fragments.ad_creation.ClassifiedFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    private List<WishAd> namelist = new ArrayList<>();
    private List<String> newlist = new ArrayList<>();
    HomeAdapter images_adapter;
    ClassifiedFragment fragment;
    Context context;

    Activity activity;

    public WishListAdapter(List<WishAd> namelist, Activity activity, Context context) {
        this.namelist = namelist;
        this.activity = activity;
        this.context = context;

    }

    public List<WishAd> getStudentist() {
        return namelist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_list_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final WishAd model = namelist.get(position);


        holder.ad_title.setText(model.getAdName());
        holder.short_desc.setText(model.getShortDescription());
        holder.price_td.setText("KD "+model.getPrice());
        holder.offer_price_td.setText("KD "+model.getOfferPrice());
        holder.date_td.setText(model.getWishAdded());
        RequestOptions requestOption = new RequestOptions()
                .placeholder(R.drawable.placeholder);

//                            Toast.makeText(getActivity(),responseBody.getClassified().getImages().get(0),Toast.LENGTH_SHORT).show();
        Glide.with(activity).load(model.getImages().get(0))
                .apply(requestOption)
                .into(holder.full_img_ad);

//        Glide.with(activity)
//                .load(model.getImages().get(0))
//                .into(holder.full_img_ad);
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MMM yyyy hh:mm aa");

        try {

            String reformattedStr = myFormat.format(fromUser.parse(model.getWishAdded()));
            holder.date_td.setText(reformattedStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.delete_imgvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(context instanceof ViewWishList){
                    ((ViewWishList)context).remove_wish(model.getWishId());
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ad_title,short_desc,price_td,offer_price_td,date_td;
        ImageView full_img_ad,delete_imgvw;


        public ViewHolder(View itemView) {
            super(itemView);

            ad_title = (TextView) itemView.findViewById(R.id.ad_title);
            short_desc = (TextView) itemView.findViewById(R.id.short_desc);

            price_td = (TextView) itemView.findViewById(R.id.price_td);
            offer_price_td = (TextView) itemView.findViewById(R.id.offer_price_td);

            date_td = (TextView) itemView.findViewById(R.id.date_td);

            full_img_ad = (ImageView) itemView.findViewById(R.id.full_img_ad);
            delete_imgvw = (ImageView) itemView.findViewById(R.id.delete_imgvw);


        }

    }




}
