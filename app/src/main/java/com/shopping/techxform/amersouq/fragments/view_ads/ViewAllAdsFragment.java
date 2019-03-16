package com.shopping.techxform.amersouq.fragments.view_ads;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.MyAdsOutput.AllAd;
import com.shopping.techxform.amersouq.RetrofitHelpers.MyAdsOutput;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.Utils.SpinnerModel;
import com.shopping.techxform.amersouq.adapters.AllAdsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by techxform on 05-Jan-19.
 */



public class ViewAllAdsFragment extends Fragment {
    Button submit_otp;
    RecyclerView all_ads_recyclerview;
//    RecyclerView all_ads_recyclerview_auc;
//    RecyclerView all_ads_recyclerview_fix;
//    RecyclerView all_ads_recyclerview_class;
    AllAdsAdapter allAdsAdapter;
    boolean page_end = false;
    int userid = 0;
    String cat_id="0";
    Activity activity;
    int page = 0;
    Context context;
    List<String> all_ads_list = new ArrayList<>();
    List<AllAd> allAds = new ArrayList<>();
//    LinearLayout Auction_Linear,Classified_Linear,Fixed_Linear;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_all_ads, container, false);

        getwindowsproperty();
        Bundle bundle=new Bundle();
        bundle=getArguments();
        userid = getActivity().getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE).getInt(Constants.user_id, 0);

        activity = getActivity();
        context = getActivity();

        cat_id=bundle.getString("cat_id");
        all_ads_recyclerview = (RecyclerView) view.findViewById(R.id.all_ads_list);
//        all_ads_recyclerview_auc = (RecyclerView) view.findViewById(R.id.all_ads_list_auc);
//        all_ads_recyclerview_class = (RecyclerView) view.findViewById(R.id.all_ads_list_clas);
//        all_ads_recyclerview_fix = (RecyclerView) view.findViewById(R.id.all_ads_list_fix);
//
//        Auction_Linear=view.findViewById(R.id.auction_id);
//        Fixed_Linear=view.findViewById(R.id.fixed_id);
//        Classified_Linear=view.findViewById(R.id.classified_id);

//        allAdsAdapter = new AllAdsAdapter(allAds, activity, R.layout.ad_list_item, context,"all");
        all_ads_recyclerview.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        all_ads_recyclerview.setItemAnimator(new DefaultItemAnimator());
        all_ads_recyclerview.setNestedScrollingEnabled(false);
        all_ads_recyclerview.setAdapter(allAdsAdapter);



        set_all_ads("0");

//        set_all_ads_Auction("0");
//        set_all_ads_Auction("0");
//        set_all_ads_Classifieds("0");
//        set_all_ads_Fixed_price("0");




        all_ads_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    // Recycle view scrolling down...
                    if (!page_end) {
                        if (recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN) == false) {
                            page++;
//                            Toast.makeText(getApplicationContext(), "Loading....", Toast.LENGTH_LONG).show();

                            String page_string = Integer.toString(page);
                            set_all_ads(page_string);
                        }
                    }
                }
            }
        });





        return view;

    }


    public void set_all_ads(String page) {

//        final ArrayList<SpinnerModel> loc_list = new ArrayList<>();
//        loc_list.add(new SpinnerModel("0", "Select Location"));
        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


//        Call<MyAdsOutput> call = apiService.get_view_ads( page);
        Call<MyAdsOutput> call = apiService.get_category_ads(cat_id, page);

        call.enqueue(new Callback<MyAdsOutput>() {
            @Override
            public void onResponse(Call<MyAdsOutput> call, retrofit2.Response<MyAdsOutput> response) {
                hud.dismiss();
                allAds.clear();
                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    MyAdsOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            if (responseBody.getAllAds().size() > 0) {
                                allAds.addAll(responseBody.getAllAds());
//                                allAdsAdapter.notifyDataSetChanged();
                                allAdsAdapter = new AllAdsAdapter(allAds, activity, R.layout.ad_list_item, context,"all");
                                all_ads_recyclerview.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                                all_ads_recyclerview.setItemAnimator(new DefaultItemAnimator());
                                all_ads_recyclerview.setNestedScrollingEnabled(false);
                                all_ads_recyclerview.setAdapter(allAdsAdapter);


                            } else {
                                page_end = true;
                            }


                        } else {
                            page_end = true;

                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<MyAdsOutput> call, Throwable t) {

                hud.dismiss();

            }
        });
    }




}

