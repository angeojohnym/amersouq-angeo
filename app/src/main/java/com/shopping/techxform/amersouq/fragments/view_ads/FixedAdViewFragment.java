package com.shopping.techxform.amersouq.fragments.view_ads;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.Models.CheckoutItem;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.AuctionOutput.ViewAuctionOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.AllCategory;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.Disable_Ad.OptionAddOutput_Disable;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.FixedPriceOutput.Fixed;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.FixedPriceOutput.ViewFixedPriceOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.MyAdsOutput.AllAd;
import com.shopping.techxform.amersouq.activities.PrePaymentPage;
import com.shopping.techxform.amersouq.adapters.AllAdsAdapter;
import com.shopping.techxform.amersouq.adapters.ImagesInnerAdapter;
import com.shopping.techxform.amersouq.fragments.ad_creation.CreationAdFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by techxform on 05-Jan-19.
 */


public class FixedAdViewFragment extends Fragment {
    Button submit_otp;
    RecyclerView image_list;
    AllAdsAdapter allAdsAdapter;
    boolean page_end = false;
    int userid = 0;
    Activity activity;
    
    double offer_price=0;
    String ad_id, type = "all", title = "", desc = "",Cat_id,Contactinfo,offerPrice,Best_Price,Reserved_Price,Start_From;
    int page = 0;
    
    LinearLayout Edit,Disable,addtocart_layout;
    ImageView cover_img;
    ImagesInnerAdapter innerAdapter;
    TextView title_td,short_desc_td,full_desc_td,seller_name_td,date_td,price_td,offer_price_td,save_price_td;
    String ClassifiedType,Location_Id,Tittle,Description,Short_Desc,Address,Contact,Offerprice,Condition,Price,Ad_Availblty;
    Context context;
    List<String> all_ads_list = new ArrayList<>();
    List<AllAd> allAds = new ArrayList<>();

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.view_fixed_price_fragment, container, false);
        getwindowsproperty();
        ad_id=getArguments().getString("ad_id");
        type=getArguments().getString("type");
//        Toast.makeText(getActivity(),ad_id,Toast.LENGTH_SHORT).show();

        context=getActivity();

        title_td=(TextView) view.findViewById(R.id.title_td) ;
        short_desc_td=(TextView) view.findViewById(R.id.short_desc_td) ;
        full_desc_td=(TextView) view.findViewById(R.id.full_desc_td) ;
        cover_img=(ImageView)view.findViewById(R.id.cover_img);
        seller_name_td=(TextView)view.findViewById(R.id.seller_name_td);
        date_td=(TextView)view.findViewById(R.id.date_td);
        price_td=(TextView)view.findViewById(R.id.price_td);
        offer_price_td=(TextView)view.findViewById(R.id.offer_price_td);
        save_price_td=(TextView)view.findViewById(R.id.save_price_td);
        image_list=(RecyclerView) view.findViewById(R.id.image_list);
        Edit=(LinearLayout) view.findViewById(R.id.edit);
        Disable=(LinearLayout) view.findViewById(R.id.disable);
        addtocart_layout=(LinearLayout) view.findViewById(R.id.addtocart_layout);

        if(type.equals("all")){
            Edit.setVisibility(View.VISIBLE);
            Disable.setVisibility(View.VISIBLE);
//            addtocart_layout.setVisibility(View.VISIBLE);
        }else
        {
            Edit.setVisibility(View.GONE);
            Disable.setVisibility(View.GONE);
            addtocart_layout.setVisibility(View.GONE);
        }

        set_fixedprice(ad_id);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CreationAdFragment creationAdFragment = new CreationAdFragment();
                Bundle bundle=new Bundle();
                bundle.putString("ad_id",ad_id);
                bundle.putString("title",title);
                bundle.putString("desc",desc);
                bundle.putString("Cat_id",Cat_id);
                AllCategory allcat=new AllCategory(Cat_id,"","","1","","",0);
                bundle.putSerializable("category_selected", allcat);
                bundle.putString("Cat","2");

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


//        Edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                List<CheckoutItem> checkoutItems = new ArrayList<>();
//                checkoutItems.add(new CheckoutItem(title, ad_id, offer_price, 1, offer_price * 1, desc));
//                Intent intent = new Intent(getActivity(), PrePaymentPage.class);
//                intent.putExtra("item_list", (Serializable) checkoutItems);
//                getActivity().startActivity(intent);
//            }
//        });
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


    public void set_fixedprice(String ad_id) {


        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<ViewFixedPriceOutput> call = apiService.get_fixedprice( ad_id);

        call.enqueue(new Callback<ViewFixedPriceOutput>() {
            @Override
            public void onResponse(Call<ViewFixedPriceOutput> call, retrofit2.Response<ViewFixedPriceOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                if (response.code() == 200) {
                    ViewFixedPriceOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {
//                            Toast.makeText(getActivity(),"200",Toast.LENGTH_SHORT).show();
                            title = responseBody.getFixed().getAdName();
                            desc = responseBody.getFixed().getShortDescription();
                            title_td.setText(responseBody.getFixed().getAdName());
                            short_desc_td.setText(responseBody.getFixed().getShortDescription());
                            full_desc_td.setText(responseBody.getFixed().getDescription());
                            full_desc_td.setText(responseBody.getFixed().getDescription());
                            Fixed fix=responseBody.getFixed();
                            Location_Id= String.valueOf(fix.getLocationsId());
                            Description=fix.getDescription();
                            Short_Desc=fix.getShortDescription();
                            Address= String.valueOf(fix.getAddressInfo());
                            Contact=fix.getContactInfo();
                            Offerprice=fix.getOfferPrice();
                            Condition= String.valueOf(fix.getConditionId());
                            Price=fix.getPrice();
                            Ad_Availblty=fix.getAdVisibility();

                            title = responseBody.getFixed().getAdName();
                            desc = responseBody.getFixed().getShortDescription();
                            title_td.setText(responseBody.getFixed().getAdName());
                            short_desc_td.setText(responseBody.getFixed().getShortDescription());
                            full_desc_td.setText(responseBody.getFixed().getDescription());
                            full_desc_td.setText(responseBody.getFixed().getDescription());
                            Cat_id=responseBody.getFixed().getCategoryId();
                            Contactinfo=responseBody.getFixed().getContactInfo();
                            offerPrice=responseBody.getFixed().getOfferPrice();

                            Start_From=responseBody.getFixed().getAddedOn();

                            List<String> allimages=responseBody.getFixed().getImages();

                            RequestOptions requestOption = new RequestOptions()
                                    .placeholder(R.drawable.placeholder);

//                            Toast.makeText(getActivity(),responseBody.getFixed().getImages().get(0),Toast.LENGTH_SHORT).show();
                            Glide.with(context).load(responseBody.getFixed().getImages().get(0))
                                    .apply(requestOption)
                                    .into(cover_img);
                            seller_name_td.setText(responseBody.getFixed().getUser());
                            date_td.setText(responseBody.getFixed().getAddedOn());

                            price_td.setText("KWD"+responseBody.getFixed().getPrice());
                            offer_price_td.setText("KWD"+responseBody.getFixed().getOfferPrice());
                            int save_price=Integer.parseInt(responseBody.getFixed().getPrice())-Integer.parseInt(responseBody.getFixed().getOfferPrice());
                            save_price_td.setText(Integer.toString(save_price));
                            innerAdapter=new ImagesInnerAdapter(allimages,activity,context,cover_img);
                            image_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                            image_list.setItemAnimator(new DefaultItemAnimator());
                            image_list.setNestedScrollingEnabled(false);
                            image_list.setAdapter(innerAdapter);


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
            public void onFailure(Call<ViewFixedPriceOutput> call, Throwable t) {

                hud.dismiss();

            }
        });
    }



}