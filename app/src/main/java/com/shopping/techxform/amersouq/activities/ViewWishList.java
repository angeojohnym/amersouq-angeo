package com.shopping.techxform.amersouq.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.DeleteWishData.DeleteWishModel;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.WishOutput.WishAd;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.WishOutput.WishOutputList;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.adapters.WishListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ViewWishList extends AppCompatActivity {

    RecyclerView wishlist_rv;
    Activity activity;
    Context context;
    WishListAdapter wishListAdapter;
    boolean page_end = false;
    int page = 0;
    List<WishAd> wishAds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wish_list);

        wishlist_rv = (RecyclerView) findViewById(R.id.wishlist_td);
        activity = this;
        context = this;


        wishlist_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    // Recycle view scrolling down...
                    if (!page_end) {
                        if (recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN) == false) {
                            page++;
//                            Toast.makeText(getApplicationContext(), "Loading....", Toast.LENGTH_LONG).show();

//                            String page_string = Integer.toString(page);
                            set_all_wish();
                        }
                    }
                }
            }
        });


        set_all_wish();


    }

    public void set_all_wish() {
        wishlist_rv.setAdapter(null);
        wishAds.clear();
        int userid = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE).getInt(Constants.user_id, 0);

        final KProgressHUD hud = KProgressHUD.create(ViewWishList.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);

        Call<WishOutputList> call = apiService.get_wish_list(Integer.toString(userid), Integer.toString(page));

        call.enqueue(new Callback<WishOutputList>() {
            @Override
            public void onResponse(Call<WishOutputList> call, retrofit2.Response<WishOutputList> response) {
                hud.dismiss();
//                wishAds.clear();
                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    WishOutputList responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            if (responseBody.getWishAds().size() > 0) {
                                wishAds.addAll(responseBody.getWishAds());
                                wishListAdapter = new WishListAdapter(wishAds, activity, context);
                                wishlist_rv.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                                wishlist_rv.setItemAnimator(new DefaultItemAnimator());
                                wishlist_rv.setNestedScrollingEnabled(false);
                                wishlist_rv.setAdapter(wishListAdapter);


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
            public void onFailure(Call<WishOutputList> call, Throwable t) {

                hud.dismiss();

            }
        });
    }

    public void remove_wish(String wish_id) {

//        final ArrayList<SpinnerModel> loc_list = new ArrayList<>();
//        loc_list.add(new SpinnerModel("0", "Select Location"));
        final KProgressHUD hud = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<DeleteWishModel> call = apiService.remove_wishlist(wish_id);

        call.enqueue(new Callback<DeleteWishModel>() {
            @Override
            public void onResponse(Call<DeleteWishModel> call, retrofit2.Response<DeleteWishModel> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    DeleteWishModel responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {
                            System.out.println("AMER SOUQ ::::  Ads deleted " + status);
                            set_all_wish();

                        } else {
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<DeleteWishModel> call, Throwable t) {

                hud.dismiss();

            }
        });
    }

}
