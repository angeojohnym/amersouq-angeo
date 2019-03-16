package com.shopping.techxform.amersouq.fragments.view_ads;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.All_Ads_Auction.AllAd_Auc;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.All_Ads_Auction.OptionAddOutput_Auc;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.All_Ads_Classified.AllAd_Classified;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.All_Ads_Classified.OptionAddOutput_Classified;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.All_Ads_Fixed.AllAd_Fixed;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.All_Ads_Fixed.OptionAddOutput_Fixed;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.MyAdsOutput.AllAd;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.MyAdsOutput.MyAdsOutput;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.Utils.SpinnerModel;
import com.shopping.techxform.amersouq.adapters.AllAdsAdapter;
import com.shopping.techxform.amersouq.adapters.AllAdsAdapter_Auction;
import com.shopping.techxform.amersouq.adapters.AllAdsAdapter_Classifieds;
import com.shopping.techxform.amersouq.adapters.AllAdsAdapter_fixed;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by techxform on 30-Nov-18.
 */


public class ViewMyAdsFragment extends Fragment {
    Button submit_otp;
//    RecyclerView my_ads;
    AllAdsAdapter_Auction allAdsAdapter;
    AllAdsAdapter_Classifieds allAdsAdapter_class;
    AllAdsAdapter_fixed allAdsAdapter_fixed;
    RecyclerView all_ads_recyclerview;
    RecyclerView all_ads_recyclerview_auc;
    RecyclerView all_ads_recyclerview_fix;
    RecyclerView all_ads_recyclerview_class;
    LinearLayout Auction_Linear,Classified_Linear,Fixed_Linear;
    boolean page_end = false;
    int userid = 0;
    Activity activity;
    int page = 0;
    Context context;
    String latitude,longitude;
    List<String> all_ads_list = new ArrayList<>();
    List<AllAd_Auc> allAds = new ArrayList<>();
    List<AllAd_Fixed> allAds_fixed = new ArrayList<>();
    List<AllAd_Classified> allAds_class = new ArrayList<>();
    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences preferences = activity.getSharedPreferences(Constants.pref_name, MODE_PRIVATE);
        latitude = preferences.getString(Constants.selected_latitude,"");
        longitude = preferences.getString(Constants.selected_longitude,"");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_my_ads, container, false);
         getwindowsproperty();
        userid = getActivity().getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE).getInt(Constants.user_id, 0);

        activity = getActivity();
        context = getActivity();

//        my_ads = (RecyclerView) view.findViewById(R.id.all);
        all_ads_recyclerview = (RecyclerView) view.findViewById(R.id.all_ads_list);
        all_ads_recyclerview_auc = (RecyclerView) view.findViewById(R.id.all_ads_list_auc);
        all_ads_recyclerview_class = (RecyclerView) view.findViewById(R.id.all_ads_list_clas);
        all_ads_recyclerview_fix = (RecyclerView) view.findViewById(R.id.all_ads_list_fix);

        Auction_Linear=view.findViewById(R.id.auction_id);
        Fixed_Linear=view.findViewById(R.id.fixed_id);
        Classified_Linear=view.findViewById(R.id.classified_id);

        set_all_ads_Auction("0");

        Auction_Linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                set_all_ads_Auction("0");

            }
        });


        Fixed_Linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                set_all_ads_Fixed_price("0");

            }
        });


        Classified_Linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                set_all_ads_Classifieds("0");

            }
        });


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
//                            set_all_ads(page_string);
                        }
                    }
                }
            }
        });


        all_ads_recyclerview_auc.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            set_all_ads_Auction(page_string);
                        }
                    }
                }
            }
        });


        all_ads_recyclerview_fix.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            set_all_ads_Fixed_price(page_string);
                        }
                    }
                }
            }
        });


        all_ads_recyclerview_class.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            set_all_ads_Classifieds(page_string);
                        }
                    }
                }
            }
        });


        return view;

    }
/*


    public void set_my_ads(String page) {

        final ArrayList<SpinnerModel> loc_list = new ArrayList<>();
        loc_list.add(new SpinnerModel("0", "Select Location"));
        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<MyAdsOutput> call = apiService.get_my_ads(Integer.toString(userid), page);

        call.enqueue(new Callback<MyAdsOutput>() {
            @Override
            public void onResponse(Call<MyAdsOutput> call, retrofit2.Response<MyAdsOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    MyAdsOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            if (responseBody.getAllAds().size() > 0) {
                                allAds.addAll(responseBody.getAllAds());
                                allAdsAdapter.notifyDataSetChanged();
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
*/

/*

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
                                allAdsAdapter.notifyDataSetChanged();
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

*/

    public void set_all_ads_Auction(String page) {

//        final ArrayList<SpinnerModel> loc_list = new ArrayList<>();
//        loc_list.add(new SpinnerModel("0", "Select Location"));
        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


//        Call<MyAdsOutput> call = apiService.get_view_ads( page);
        Call<OptionAddOutput_Auc> call = apiService.get_category_ads_Auc("3", page,latitude,longitude);

        call.enqueue(new Callback<OptionAddOutput_Auc>() {
            @Override
            public void onResponse(Call<OptionAddOutput_Auc> call, retrofit2.Response<OptionAddOutput_Auc> response) {
                hud.dismiss();
                allAds.clear();
                String URL = call.request().url().toString();
                System.out.println("Retrofit URL :AdS 111  " + URL);
                if (response.code() == 200) {

                    OptionAddOutput_Auc responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            if (responseBody.getAllAds().size() > 0) {
                                all_ads_recyclerview_auc.setVisibility(View.VISIBLE);
                                all_ads_recyclerview_class.setVisibility(View.GONE);
                                all_ads_recyclerview_fix.setVisibility(View.GONE);
                                allAds.addAll(responseBody.getAllAds());
                                allAdsAdapter = new AllAdsAdapter_Auction(allAds, activity, R.layout.ad_list_item, context,"all");
                                all_ads_recyclerview_auc.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                                all_ads_recyclerview_auc.setItemAnimator(new DefaultItemAnimator());
                                all_ads_recyclerview_auc.setNestedScrollingEnabled(false);
                                all_ads_recyclerview_auc.setAdapter(allAdsAdapter);
                                allAdsAdapter.notifyDataSetChanged();
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
            public void onFailure(Call<OptionAddOutput_Auc> call, Throwable t) {

                hud.dismiss();

            }
        });
    }


    public void set_all_ads_Classifieds(String page) {

//        final ArrayList<SpinnerModel> loc_list = new ArrayList<>();
//        loc_list.add(new SpinnerModel("0", "Select Location"));
        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


//        Call<MyAdsOutput> call = apiService.get_view_ads( page);
        Call<OptionAddOutput_Classified> call = apiService.get_category_ads_Classified("1", page);

        call.enqueue(new Callback<OptionAddOutput_Classified>() {
            @Override
            public void onResponse(Call<OptionAddOutput_Classified> call, retrofit2.Response<OptionAddOutput_Classified> response) {
                hud.dismiss();
                allAds_class.clear();
                String URL = call.request().url().toString();
                System.out.println("Retrofit URL :AdS 2 " + URL);
                if (response.code() == 200) {
                    OptionAddOutput_Classified responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            if (responseBody.getAllAds().size() > 0) {
                                all_ads_recyclerview_auc.setVisibility(View.GONE);
                                all_ads_recyclerview_class.setVisibility(View.VISIBLE);
                                all_ads_recyclerview_fix.setVisibility(View.GONE);
                                allAds_class.addAll(responseBody.getAllAds());

                                allAdsAdapter_class = new AllAdsAdapter_Classifieds(allAds_class, activity, R.layout.ad_list_item, context,"all");
                                all_ads_recyclerview_class.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                                all_ads_recyclerview_class.setItemAnimator(new DefaultItemAnimator());
                                all_ads_recyclerview_class.setNestedScrollingEnabled(false);
                                all_ads_recyclerview_class.setAdapter(allAdsAdapter_class);
                                allAdsAdapter_class.notifyDataSetChanged();
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
            public void onFailure(Call<OptionAddOutput_Classified> call, Throwable t) {

                hud.dismiss();

            }
        });
    }


    public void set_all_ads_Fixed_price(String page) {

//        final ArrayList<SpinnerModel> loc_list = new ArrayList<>();
//        loc_list.add(new SpinnerModel("0", "Select Location"));
        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


//        Call<MyAdsOutput> call = apiService.get_view_ads( page);
        Call<OptionAddOutput_Fixed> call = apiService.get_category_ads_Fixed("2", page);

        call.enqueue(new Callback<OptionAddOutput_Fixed>() {
            @Override
            public void onResponse(Call<OptionAddOutput_Fixed> call, retrofit2.Response<OptionAddOutput_Fixed> response) {
                hud.dismiss();
                allAds_fixed.clear();
                String URL = call.request().url().toString();
                System.out.println("Retrofit URL :3 " + URL);
                if (response.code() == 200) {
                    OptionAddOutput_Fixed responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            if (responseBody.getAllAds().size() > 0) {
                                all_ads_recyclerview_auc.setVisibility(View.GONE);
                                all_ads_recyclerview_class.setVisibility(View.GONE);
                                all_ads_recyclerview_fix.setVisibility(View.VISIBLE);
                                allAds_fixed.addAll(responseBody.getAllAds());

                                allAdsAdapter_fixed = new AllAdsAdapter_fixed(allAds_fixed, activity, R.layout.ad_list_item, context,"all");
                                all_ads_recyclerview_fix.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                                all_ads_recyclerview_fix.setItemAnimator(new DefaultItemAnimator());
                                all_ads_recyclerview_fix.setNestedScrollingEnabled(false);
                                all_ads_recyclerview_fix.setAdapter(allAdsAdapter_fixed);

                                allAdsAdapter_fixed.notifyDataSetChanged();
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
            public void onFailure(Call<OptionAddOutput_Fixed> call, Throwable t) {

                hud.dismiss();

            }
        });
    }




}