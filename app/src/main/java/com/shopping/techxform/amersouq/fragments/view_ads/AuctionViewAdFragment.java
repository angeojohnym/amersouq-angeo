package com.shopping.techxform.amersouq.fragments.view_ads;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.bidinput.BidInputModel;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.AuctionOutput.TopBidOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.AuctionOutput.ViewAuctionOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.AllCategory;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.Disable_Ad.OptionAddOutput_Disable;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.MyAdsOutput.AllAd;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.bidoutput.BidOutput;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.adapters.AllAdsAdapter;
import com.shopping.techxform.amersouq.adapters.ImagesInnerAdapter;
import com.shopping.techxform.amersouq.fragments.ad_creation.AuctionFragment;
import com.shopping.techxform.amersouq.fragments.ad_creation.CreationAdFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by techxform on 05-Jan-19.
 */


public class AuctionViewAdFragment extends Fragment {
    Button submit_otp;
    RecyclerView image_list;
    String auto_bid_string="0";

    AllAdsAdapter allAdsAdapter;
    boolean page_end = false;
    int userid = 0;
    double buynow_price = 0;
    ImagesInnerAdapter innerAdapter;
    Activity activity;
    int page = 0;
    RelativeLayout home_live_layout;
    Context context;
    List<String> all_ads_list = new ArrayList<>();
    List<AllAd> allAds = new ArrayList<>();
    String ad_id, type = "all", title = "", desc = "",Cat_id,Contactinfo,Best_Price,Reserved_Price,Start_From;
    LinearLayout Edit, Disable, bid_now_btn;
    ImageView cover_img;
    RecyclerView images_list;
    TextView bid_amt_td, bidone, bidtwo, bidthree;
    TextView title_td, short_desc_td, full_desc_td, seller_name_td, date_td, reserve_td, base_td, end_datetime_td;

    AllCategory current_category = null;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.view_auction_fragment, container, false);
        getwindowsproperty();
        context = getActivity();
        ad_id = getArguments().getString("ad_id");
        type = getArguments().getString("type");
        bid_now_btn = (LinearLayout) view.findViewById(R.id.bid_now_btn);
//        Toast.makeText(getActivity(), ad_id, Toast.LENGTH_SHORT).show();
        title_td = (TextView) view.findViewById(R.id.title_td);
        short_desc_td = (TextView) view.findViewById(R.id.short_desc_td);
        full_desc_td = (TextView) view.findViewById(R.id.full_desc_td);
        image_list = (RecyclerView) view.findViewById(R.id.image_list);
        seller_name_td = (TextView) view.findViewById(R.id.seller_name_td);
        date_td = (TextView) view.findViewById(R.id.date_td);
        end_datetime_td = (TextView) view.findViewById(R.id.end_datetime_td);
        reserve_td = (TextView) view.findViewById(R.id.reserve_td);
        base_td = (TextView) view.findViewById(R.id.base_td);
        cover_img = (ImageView) view.findViewById(R.id.cover_img);
        images_list = (RecyclerView) view.findViewById(R.id.image_list);
        home_live_layout = (RelativeLayout) view.findViewById(R.id.home_live_layout);

        Edit = (LinearLayout) view.findViewById(R.id.chat_seller_layout);
        Disable = (LinearLayout) view.findViewById(R.id.buynow_layout);

        bid_amt_td = (TextView) view.findViewById(R.id.bid_amt_td);
        bidone = (TextView) view.findViewById(R.id.bidone);
        bidtwo = (TextView) view.findViewById(R.id.bidtwo);
        bidthree = (TextView) view.findViewById(R.id.bidthree);

        if (type.equals("all")) {
            Edit.setVisibility(View.VISIBLE);
            Disable.setVisibility(View.VISIBLE);
            home_live_layout.setVisibility(View.VISIBLE);
        } else {
            Edit.setVisibility(View.GONE);
            Disable.setVisibility(View.GONE);
            home_live_layout.setVisibility(View.GONE);
        }


        set_auction(ad_id);
       /* bid_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.bid_ad_layout, null);
                alertDialogBuilder.setView(view1);
                alertDialogBuilder.setCancelable(true);
                final AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();
                final CheckBox auto_bid_cb = (CheckBox) dialog.findViewById(R.id.auto_bid_cb);
                final LinearLayout bid_layout = (LinearLayout) dialog.findViewById(R.id.bid_layout);

                TextView nxt_btn = (TextView) dialog.findViewById(R.id.nxt_btn);
                final EditText pricelimit_td = (EditText) dialog.findViewById(R.id.pricelimit_td);
                final EditText increment_td = (EditText) dialog.findViewById(R.id.increment_td);
                final EditText enter_bid_td = (EditText) dialog.findViewById(R.id.enter_bid_td);
                auto_bid_cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (auto_bid_cb.isChecked()) {
                            auto_bid_string="1";
                            bid_layout.setVisibility(View.VISIBLE);
                        } else {
                            auto_bid_string="0";
                            bid_layout.setVisibility(View.GONE);
                        }
                    }
                });

                nxt_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
                        userid=sharedPreferences.getInt(Constants.user_id,0);
                        dialog.dismiss();
                        String increment="0",price="0",enter_bid="0";
                        if(increment_td.getText().toString().trim().equals("")){
                            increment="0";
                        }else {
                            increment=increment_td.getText().toString().trim();
                        }

                        if(pricelimit_td.getText().toString().trim().equals("")){
                            price="0";
                        }else {
                            price=pricelimit_td.getText().toString().trim();
                        }

                        if(enter_bid_td.getText().toString().trim().equals("")){
                            enter_bid="0";
                        }else {
                            enter_bid=enter_bid_td.getText().toString().trim();
                        }

                        BidInputModel bidInputModel=new BidInputModel(auto_bid_string,Integer.toString(userid),ad_id,increment,
                                price,enter_bid);
                        set_bidding(bidInputModel);
                    }
                });


            }
        });*/
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CreationAdFragment creationAdFragment = new CreationAdFragment();
                Bundle bundle=new Bundle();
                bundle.putString("ad_id",ad_id);
                bundle.putString("Cat","0");
                bundle.putString("title",title);
                bundle.putString("desc",desc);
                bundle.putString("Cat_id",Cat_id);
                AllCategory allcat=new AllCategory(Cat_id,"","","1","","",0);
                bundle.putSerializable("category_selected", allcat);
                bundle.putString("Cat","3");

                bundle.putString("Contactinfo",Contactinfo);
                bundle.putString("Best_Price",Best_Price);
                bundle.putString("Reserved_Price",Reserved_Price);
                bundle.putString("Start_From",Start_From);


                bundle.putString("short_desc_td", String.valueOf(short_desc_td.getText().toString()));
                bundle.putString("full_desc_td", String.valueOf(full_desc_td.getText().toString()));
                creationAdFragment.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, creationAdFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });
        Disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Disable_Ad(ad_id);

            }
        });
        return view;

    }

    public void Disable_Ad(String ad_id) {

//        final ArrayList<SpinnerModel> loc_list = new ArrayList<>();
//        loc_list.add(new SpinnerModel("0", "Select Location"));
        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<OptionAddOutput_Disable> call = apiService.delete_ad_classified( ad_id);

        call.enqueue(new Callback<OptionAddOutput_Disable>() {
            @Override
            public void onResponse(Call<OptionAddOutput_Disable> call, retrofit2.Response<OptionAddOutput_Disable> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    OptionAddOutput_Disable responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {
                            System.out.println("AMER SOUQ ::::  Ads deleted " + status);
                            getActivity().finish();
                            Toast.makeText(getActivity(), "Ad Disabled...", Toast.LENGTH_SHORT).show();
                            Disable.setEnabled(false);
                            Edit.setEnabled(false);

                        } else {
                            Toast.makeText(getActivity(),"Something went wrong..Try again after sometime",Toast.LENGTH_SHORT).show();
                            activity.onBackPressed();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<OptionAddOutput_Disable> call, Throwable t) {

                hud.dismiss();

            }
        });
    }

    public void set_bidding(BidInputModel bidInputModel){


        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<BidOutput> call = apiService.set_auction_bids(bidInputModel);

        call.enqueue(new Callback<BidOutput>() {
            @Override
            public void onResponse(Call<BidOutput> call, retrofit2.Response<BidOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    BidOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                           set_auction_bids(ad_id);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<BidOutput> call, Throwable t) {

                hud.dismiss();

            }
        });


    }

    public void set_auction(final String ad_id) {


        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<ViewAuctionOutput> call = apiService.get_auction(ad_id);

        call.enqueue(new Callback<ViewAuctionOutput>() {
            @Override
            public void onResponse(Call<ViewAuctionOutput> call, retrofit2.Response<ViewAuctionOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL 12 : " + URL);
                if (response.code() == 200) {
                    ViewAuctionOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {
//                            Toast.makeText(getActivity(),"200",Toast.LENGTH_SHORT).show();
                            title = responseBody.getAuction().getAdName();
                            desc = responseBody.getAuction().getShortDescription();
                            title_td.setText(responseBody.getAuction().getAdName());
                            short_desc_td.setText(responseBody.getAuction().getShortDescription());
                            full_desc_td.setText(responseBody.getAuction().getDescription());
                            full_desc_td.setText(responseBody.getAuction().getDescription());
                            Cat_id=responseBody.getAuction().getCategoryId();
                            Contactinfo=responseBody.getAuction().getContactInfo();
                            Best_Price=responseBody.getAuction().getLowerLimit();
                            Reserved_Price=responseBody.getAuction().getReservePrice();
                            Start_From=responseBody.getAuction().getAddedOn();

                            SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            SimpleDateFormat myFormat = new SimpleDateFormat("dd MMM yyyy hh:mm aa");

                            try {

                                String reformattedStr = myFormat.format(fromUser.parse(responseBody.getAuction().getAuctionToDateTime()));
                                end_datetime_td.setText(reformattedStr);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
//                            end_datetime_td.setText(responseBody.getAuction().getAuctionToDateTime());
                            try {
                                buynow_price = Double.parseDouble(responseBody.getAuction().getReservePrice());
                            } catch (Exception e) {
                                buynow_price = 0;
                            }
                            List<String> allimages = responseBody.getAuction().getImages();


                            RequestOptions requestOption = new RequestOptions()
                                    .placeholder(R.drawable.placeholder);

//                            Toast.makeText(getActivity(),responseBody.getClassified().getImages().get(0),Toast.LENGTH_SHORT).show();
                            Glide.with(context).load(responseBody.getAuction().getImages().get(0))
                                    .apply(requestOption)
                                    .into(cover_img);
                            seller_name_td.setText(responseBody.getAuction().getUser());
                            date_td.setText(responseBody.getAuction().getAddedOn());
                            base_td.setText("Base\n" + responseBody.getAuction().getLowerLimit());
                            reserve_td.setText("Reserve\n" + responseBody.getAuction().getReservePrice());


                            innerAdapter = new ImagesInnerAdapter(allimages, activity, context, cover_img);
                            image_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                            image_list.setItemAnimator(new DefaultItemAnimator());
                            image_list.setNestedScrollingEnabled(false);
                            image_list.setAdapter(innerAdapter);

                            set_auction_bids(ad_id);

                        } else {
                            Toast.makeText(getActivity(), "Something went wrong..Try again after sometime", Toast.LENGTH_SHORT).show();
                            activity.onBackPressed();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<ViewAuctionOutput> call, Throwable t) {

                hud.dismiss();

            }
        });
    }

    public void set_auction_bids(String ad_id) {


        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<TopBidOutput> call = apiService.get_auction_bids(ad_id);

        call.enqueue(new Callback<TopBidOutput>() {
            @Override
            public void onResponse(Call<TopBidOutput> call, retrofit2.Response<TopBidOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    TopBidOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            try {

                                bid_amt_td.setText(responseBody.getAlert().get(0).getBidPrice());
                                bidone.setText(responseBody.getAlert().get(0).getBidPrice());

                            } catch (Exception e) {
                            }
                            try {

                                bidtwo.setText(responseBody.getAlert().get(1).getBidPrice());

                            } catch (Exception e) {
                            }
                            try {

                                bidthree.setText(responseBody.getAlert().get(2).getBidPrice());
                                bidthree.setText(responseBody.getAlert().get(2).getBidPrice());

                            } catch (Exception e) {
                            }


                        } else {
//                            Toast.makeText(getActivity(),"Something went wrong..Try again after sometime",Toast.LENGTH_SHORT).show();
//                            activity.onBackPressed();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<TopBidOutput> call, Throwable t) {

                hud.dismiss();

            }
        });
    }


}