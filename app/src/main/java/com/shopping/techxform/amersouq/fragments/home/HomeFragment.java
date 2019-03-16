package com.shopping.techxform.amersouq.fragments.home;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.Models.All_products;
import com.shopping.techxform.amersouq.Models.ViewSingleProductResponse;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData.ForsaleCategoryItem;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData.HomepageOutput;
import com.shopping.techxform.amersouq.Utils.CategoryIconModel;
import com.shopping.techxform.amersouq.activities.ProductDetails.ProductDetailsActivityNew;
import com.shopping.techxform.amersouq.activities.ViewAdvertisement.ViewAllAds;
import com.shopping.techxform.amersouq.adapters.AlertsAdapter;
import com.shopping.techxform.amersouq.adapters.BannerAdapter;
import com.shopping.techxform.amersouq.adapters.CategoryHomeIconAdapter;
import com.shopping.techxform.amersouq.adapters.HomeAdapter;
import com.shopping.techxform.amersouq.adapters.HomeAuctionAdapter;
import com.shopping.techxform.amersouq.adapters.HomeFeaturedAdAdapter;
import com.shopping.techxform.amersouq.adapters.HomeFeaturedProductAdapter;
import com.shopping.techxform.amersouq.adapters.HomepageLiveAuctionAdapter;
import com.shopping.techxform.amersouq.listner.RecyclerClick;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by techxform on 21-Nov-18.
 */


public class HomeFragment extends Fragment {
    TextView sign_up_td, view_all_featureads;
    RecyclerView banner_list, homepage_auction_list;
    Activity activity;
    ImageView company_ads_2, company_ads_1;
    HomeFeaturedAdAdapter featuredAdAdapter;
    HomeFeaturedProductAdapter featuredProductAdapter;
    Context context;
    CategoryHomeIconAdapter iconAdapter;
    ArrayList<CategoryIconModel> categoryIconModels = new ArrayList<>();
    AlertsAdapter alertsAdapter;
    RecyclerView featured_list, ascollection_list, categories_list, auctions_list;
    List<String> featured_listitems = new ArrayList<>();
    List<String> auction_listitems = new ArrayList<>();

    List<String> ascollection_listitems = new ArrayList<>();
    HomeAdapter featuredAdapter, ascollectionAdapter, categoryAdapter, auctionAdapter;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        getwindowsproperty();
        featured_list = view.findViewById(R.id.featured_ads_td_layout);
        ascollection_list = view.findViewById(R.id.as_collections_layout);
        categories_list = view.findViewById(R.id.categories_layout);
        auctions_list = view.findViewById(R.id.auctions_list);
        banner_list = view.findViewById(R.id.banner_list);
        homepage_auction_list = view.findViewById(R.id.homepage_auction_list);
        company_ads_1 = view.findViewById(R.id.company_ads_1);
        company_ads_2 = view.findViewById(R.id.company_ads_2);
        view_all_featureads = view.findViewById(R.id.view_all_featureads);
        view_all_featureads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ViewAllAds.class);
                intent.putExtra("type", "all");
                intent.putExtra("cat_id","0");

                activity.startActivity(intent);
            }
        });
//        view_all_featureads.setPaintFlags(view_all_featureads.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        activity = getActivity();
        context = getActivity();

        for (int i = 0; i < 16; i++) {

            featured_listitems.add("abc");
            ascollection_listitems.add("fgh");
        }

        for (int j = 0; j < 4; j++) {

            auction_listitems.add("abc");
        }

//        sign_up_td=view.findViewById(R.id.sign_up_td);
//
//
//        sign_up_td.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                SignUpFragment signUpFragment=new SignUpFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, signUpFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });


//        alertsAdapter = new AlertsAdapter(auction_listitems, activity, R.layout.layout_homepage_auction, context);
//        homepage_auction_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
//        homepage_auction_list.setItemAnimator(new DefaultItemAnimator());
//        homepage_auction_list.setNestedScrollingEnabled(false);
//        homepage_auction_list.setAdapter(alertsAdapter);


//        auctionAdapter = new HomeAdapter(ascollection_listitems, activity, R.layout.auction_item, context, 1, new RecyclerClick() {
//            @Override
//            public void onClickItem(View view, int position) {
//            }
//        });
//
//        auctions_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
//        auctions_list.setItemAnimator(new DefaultItemAnimator());
//        auctions_list.setNestedScrollingEnabled(false);
//        auctions_list.setAdapter(auctionAdapter);


        set_homepage();
        return view;

    }

    private void addProductData(String productId) {
        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<ViewSingleProductResponse> call = apiService.view_single_product(productId, 0);

        call.enqueue(new Callback<ViewSingleProductResponse>() {
            @Override
            public void onResponse(Call<ViewSingleProductResponse> call, retrofit2.Response<ViewSingleProductResponse> response) {
                hud.dismiss();
                ViewSingleProductResponse productsResponse = response.body();
                if (productsResponse != null && productsResponse.getSingle_product() != null &&
                        productsResponse.getSingle_product().size() > 0) {
                    All_products all_products = new All_products();
                    all_products.setFull_desc(productsResponse.getSingle_product().get(0).getFull_desc());
                    all_products.setShort_desc(productsResponse.getSingle_product().get(0).getShort_desc());
                    all_products.setProduct_id(productsResponse.getSingle_product().get(0).getProduct_id());
                    all_products.setImages(productsResponse.getSingle_product().get(0).getImages());
                    all_products.setIn_stock(productsResponse.getSingle_product().get(0).getIn_stock());
                    all_products.setProduct_name(productsResponse.getSingle_product().get(0).getProduct_name());
                    all_products.setPrice(productsResponse.getSingle_product().get(0).getPrice());
                    all_products.setOffer_price(productsResponse.getSingle_product().get(0).getOffer_price());
                    Intent intent = new Intent(getActivity(), ProductDetailsActivityNew
                            .class);
                    intent.putExtra("PRODUCT", all_products);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ViewSingleProductResponse> call, Throwable t) {
                hud.dismiss();

            }
        });
    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */


    public void set_homepage() {


        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<HomepageOutput> call = apiService.view_homepage();

        call.enqueue(new Callback<HomepageOutput>() {
            @Override
            public void onResponse(Call<HomepageOutput> call, retrofit2.Response<HomepageOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    final HomepageOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {

                            List<ForsaleCategoryItem> forsaleCategories = responseBody.getForsaleCategory();
                            forsaleCategories.add(new ForsaleCategoryItem("0", "See More", "", "", "", "0", "0", "", "", ""));
                            iconAdapter = new CategoryHomeIconAdapter(forsaleCategories, activity, context);
                            categories_list.setLayoutManager(new GridLayoutManager(context, 4));
                            categories_list.setItemAnimator(new DefaultItemAnimator());
                            categories_list.setNestedScrollingEnabled(false);
                            categories_list.setAdapter(iconAdapter);


                            featuredAdAdapter = new HomeFeaturedAdAdapter(responseBody.getFeaturedAds(), activity, context);
                            featured_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                            featured_list.setItemAnimator(new DefaultItemAnimator());
                            featured_list.setNestedScrollingEnabled(false);
                            featured_list.setAdapter(featuredAdAdapter);

                            featuredProductAdapter = new HomeFeaturedProductAdapter(responseBody.getFeaturedProducts(), activity, context, new RecyclerClick() {
                                @Override
                                public void onClickItem(View view, int position) {
                                    addProductData(responseBody.getFeaturedProducts().get(position).getProductId());
                                }
                            });
                            ascollection_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                            ascollection_list.setItemAnimator(new DefaultItemAnimator());
                            ascollection_list.setNestedScrollingEnabled(false);
                            ascollection_list.setAdapter(featuredProductAdapter);


                            if (responseBody.getBanner().size() == 0) {
                                company_ads_1.setVisibility(View.GONE);
                                company_ads_2.setVisibility(View.GONE);

                            }
                            if (responseBody.getBanner().size() == 1) {
                                company_ads_2.setVisibility(View.GONE);
                                company_ads_1.setVisibility(View.VISIBLE);
                                company_ads_1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String url = responseBody.getBanner().get(0).getUrl();
                                        Intent i = new Intent(Intent.ACTION_VIEW);
                                        i.setData(Uri.parse(url));
                                        startActivity(i);
                                    }
                                });
                                RequestOptions requestOption = new RequestOptions()
                                        .placeholder(R.drawable.placeholder);

//                            Toast.makeText(getActivity(),responseBody.getClassified().getImages().get(0),Toast.LENGTH_SHORT).show();
                                Glide.with(context).load(responseBody.getBanner().get(0).getImage())
                                        .apply(requestOption)
                                        .into(company_ads_1);

                            }
                            if (responseBody.getBanner().size() > 1) {
                                company_ads_2.setVisibility(View.VISIBLE);
                                company_ads_1.setVisibility(View.VISIBLE);
                                RequestOptions requestOption = new RequestOptions()
                                        .placeholder(R.drawable.placeholder);

//                            Toast.makeText(getActivity(),responseBody.getClassified().getImages().get(0),Toast.LENGTH_SHORT).show();
                                Glide.with(context).load(responseBody.getBanner().get(0).getImage())
                                        .apply(requestOption)
                                        .into(company_ads_1);

                                Glide.with(context).load(responseBody.getBanner().get(1).getImage())
                                        .apply(requestOption)
                                        .into(company_ads_2);

                                company_ads_1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String url = responseBody.getBanner().get(0).getUrl();
                                        Intent i = new Intent(Intent.ACTION_VIEW);
                                        i.setData(Uri.parse(url));
                                        startActivity(i);
                                    }
                                });

                                company_ads_2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String url = responseBody.getBanner().get(1).getUrl();
                                        Intent i = new Intent(Intent.ACTION_VIEW);
                                        i.setData(Uri.parse(url));
                                        startActivity(i);
                                    }
                                });
                            }

                            BannerAdapter bannerAdapter = new BannerAdapter(responseBody.getBanner(), activity, context);
                            banner_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                            banner_list.setItemAnimator(new DefaultItemAnimator());
                            banner_list.setNestedScrollingEnabled(false);
                            banner_list.setAdapter(bannerAdapter);

                            HomepageLiveAuctionAdapter liveAuctionAdapter = new HomepageLiveAuctionAdapter(responseBody.getLiveAuction(), activity, context);
                            homepage_auction_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                            homepage_auction_list.setItemAnimator(new DefaultItemAnimator());
                            homepage_auction_list.setNestedScrollingEnabled(false);
                            homepage_auction_list.setAdapter(liveAuctionAdapter);

                            HomeAuctionAdapter auctionAdapter = new HomeAuctionAdapter(responseBody.getAuctions(), activity, context);
                            auctions_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                            auctions_list.setItemAnimator(new DefaultItemAnimator());
                            auctions_list.setNestedScrollingEnabled(false);
                            auctions_list.setAdapter(auctionAdapter);


                        } else {
//                            Toast.makeText(getActivity(),"Something went wrong..Try again after sometime",Toast.LENGTH_SHORT).show();
                            activity.onBackPressed();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                hud.dismiss();
            }

            @Override
            public void onFailure(Call<HomepageOutput> call, Throwable t) {

                hud.dismiss();

            }
        });
    }

    public void insert_fcmtoken(String fcm_token) {

    }


}
