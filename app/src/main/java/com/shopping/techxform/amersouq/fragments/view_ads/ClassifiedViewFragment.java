package com.shopping.techxform.amersouq.fragments.view_ads;

import android.Manifest;
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
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.AllCategory;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ClassifiedData.Classified;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ClassifiedData.ViewClassifiedOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.Disable_Ad.OptionAddOutput_Disable;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.MyAdsOutput.AllAd;
import com.shopping.techxform.amersouq.adapters.AllAdsAdapter;
import com.shopping.techxform.amersouq.adapters.ImagesInnerAdapter;
import com.shopping.techxform.amersouq.fragments.ad_creation.CreationAdFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by techxform on 05-Jan-19.
 */




public class ClassifiedViewFragment extends Fragment {
    Button submit_otp;
    RecyclerView all_ads_recyclerview;
    AllAdsAdapter allAdsAdapter;
    boolean page_end = false;
    int userid = 0;
    ImagesInnerAdapter innerAdapter;
//    LinearLayout callnow_layout, Edit_Ad;
    Activity activity;
    int page = 0;
    String ad_id,type="all";
    String phone_num="000000";
    TextView title_td,short_desc_td,full_desc_td,price_td,offer_price_td,save_price_td,seller_name_td,date_td;
    LinearLayout Edit,Disable;
    Context context;
    ImageView cover_img;
    RecyclerView image_list;
    List<String> all_ads_list = new ArrayList<>();
    List<AllAd> allAds = new ArrayList<>();
    String ClassifiedType,Location_Id,Tittle,Description,Short_Desc,Address,Contact,Offerprice,Condition,Price,Ad_Availblty;
    String  title = "", desc = "",Cat_id,Contactinfo,Best_Price,Reserved_Price,Start_From,offerPrice;

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
//            Toast.makeText(HomePage.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            //Camera Intent and access Location logic here
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(getActivity(), "Permission Denied\n" + deniedPermissions.toString() + "\n Kindly allow all the permissions", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    };
    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }
//    PhotoView imageView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.view_classified_ad_fragment, container, false);
        getwindowsproperty();
        ad_id=getArguments().getString("ad_id");
        type = getArguments().getString("type");
        cover_img=(ImageView)view.findViewById(R.id.cover_img) ;
        image_list=(RecyclerView) view.findViewById(R.id.image_list) ;

        System.out.println("ADS   111111111");

        context=getActivity();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            new TedPermission(getActivity())
                    .setPermissionListener(permissionlistener)
                    .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(Manifest.permission.CALL_PHONE)
                    .check();
        }

        title_td=(TextView) view.findViewById(R.id.title_td) ;
        short_desc_td=(TextView) view.findViewById(R.id.short_desc_td) ;
        full_desc_td=(TextView) view.findViewById(R.id.full_desc_td) ;
        price_td=(TextView) view.findViewById(R.id.price_td) ;
        offer_price_td=(TextView) view.findViewById(R.id.offer_price_td) ;
        save_price_td=(TextView) view.findViewById(R.id.save_price_td) ;
        seller_name_td=(TextView)view.findViewById(R.id.seller_name_td);
        date_td=(TextView)view.findViewById(R.id.date_td);
//imageView=(PhotoView)view.findViewById(R.id.imageView);
        Edit=(LinearLayout) view.findViewById(R.id.edit) ;
        Disable=(LinearLayout) view.findViewById(R.id.disable) ;

//        Edit_Ad =(LinearLayout) view.findViewById(R.id.Edit) ;
//        callnow_layout=(LinearLayout) view.findViewById(R.id.callnow_layout) ;
        if(type.equals("all")){
            Edit.setVisibility(View.VISIBLE);
            Disable.setVisibility(View.VISIBLE);
        }else
        {
            Edit.setVisibility(View.VISIBLE);
            Disable.setVisibility(View.VISIBLE);
        }
        set_classified(ad_id);

//        callnow_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone_num));
//                startActivity(intent);
//            }
//        });

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Intent in=new Intent(getActivity(),Edit_Ad_Classified.class);
//                Bundle bundle=new Bundle();
//                bundle.putString("ad_id",ad_id);
//                bundle.putString("location_id",Location_Id);
//                bundle.putString("Classi_Type",ClassifiedType);
//                bundle.putString("Short_Desc",Short_Desc);
//                bundle.putString("Address",Address);
//                bundle.putString("Contact",Contact);
//                bundle.putString("Offerprice",Offerprice);
//                bundle.putString("Price",Price);
//                bundle.putString("Condition",Condition);
//                bundle.putString("Ad_Availblty",Ad_Availblty);
//                in.putExtras(bundle);
//                startActivity(in);

                CreationAdFragment creationAdFragment = new CreationAdFragment();
                Bundle bundle=new Bundle();
                bundle.putString("ad_id",ad_id);
                bundle.putString("title",title);
                bundle.putString("desc",desc);
                bundle.putString("Cat","0");
                bundle.putString("Cat_id",Cat_id);
                AllCategory allcat=new AllCategory(Cat_id,"","","1","","",0);
                bundle.putSerializable("category_selected", allcat);
                bundle.putString("Cat","1");

                bundle.putString("Contactinfo",Contactinfo);
                bundle.putString("ClassifiedType",ClassifiedType);
                bundle.putString("Best_Price",Best_Price);
                bundle.putString("Reserved_Price",Reserved_Price);
                bundle.putString("Start_From",Start_From);
                bundle.putString("offerPrice",offerPrice);
                bundle.putString("Address",Address);
                bundle.putString("Price",Price);


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

                Disable(ad_id);

            }
        });

        return view;

    }




    public void set_classified(String ad_id) {

//        final ArrayList<SpinnerModel> loc_list = new ArrayList<>();
//        loc_list.add(new SpinnerModel("0", "Select Location"));
        final KProgressHUD hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<ViewClassifiedOutput> call = apiService.get_classified( ad_id);

        call.enqueue(new Callback<ViewClassifiedOutput>() {
            @Override
            public void onResponse(Call<ViewClassifiedOutput> call, retrofit2.Response<ViewClassifiedOutput> response) {
                hud.dismiss();

                String URL = call.request().url().toString();
                System.out.println("SHANIL  111  "+URL);
                System.out.println("  " + URL);
                if (response.code() == 200) {
                    ViewClassifiedOutput responseBody = response.body();

                    try {
                        String status = responseBody.getCode();

                        if (status.equals("200")) {
//                            Toast.makeText(getActivity(),"200",Toast.LENGTH_SHORT).show();
                            title_td.setText(responseBody.getClassified().getAdName());
                            short_desc_td.setText(responseBody.getClassified().getShortDescription());
                            full_desc_td.setText(responseBody.getClassified().getDescription());

                            Classified classi=responseBody.getClassified();
                            ClassifiedType=classi.getClassifiedType();
                            Location_Id= String.valueOf(classi.getLocationsId());
                            Description=classi.getDescription();
                            Short_Desc=classi.getShortDescription();
                            Address= String.valueOf(classi.getAddressInfo());
                            Contact=classi.getContactInfo();
                            Offerprice=classi.getOfferPrice();
                            Condition= String.valueOf(classi.getConditionId());
                            Price=classi.getPrice();
                            Ad_Availblty=classi.getAdVisibility();

                            title = responseBody.getClassified().getAdName();
                            desc = responseBody.getClassified().getShortDescription();
                            title_td.setText(responseBody.getClassified().getAdName());
                            short_desc_td.setText(responseBody.getClassified().getShortDescription());
                            full_desc_td.setText(responseBody.getClassified().getDescription());
                            full_desc_td.setText(responseBody.getClassified().getDescription());
                            Cat_id=responseBody.getClassified().getCategoryId();
                            Contactinfo=responseBody.getClassified().getContactInfo();
                            offerPrice=responseBody.getClassified().getOfferPrice();

                            Start_From=responseBody.getClassified().getAddedOn();


                            List<String> allimages=responseBody.getClassified().getImages();

                            price_td.setText("KWD "+responseBody.getClassified().getPrice());
                            offer_price_td.setText("KWD "+responseBody.getClassified().getOfferPrice());
                            phone_num=responseBody.getClassified().getContactInfo();

                            RequestOptions requestOption = new RequestOptions()
                                    .placeholder(R.drawable.placeholder);

//                            Toast.makeText(getActivity(),responseBody.getClassified().getImages().get(0),Toast.LENGTH_SHORT).show();
                            Glide.with(context).load(responseBody.getClassified().getImages().get(0))
                                    .apply(requestOption)
                                    .into(cover_img);
//                            Glide.with(context).load(responseBody.getClassified().getImages().get(0))
//                                    .apply(requestOption)
//                                    .into(imageView);

                            seller_name_td.setText(responseBody.getClassified().getUser());
                            date_td.setText(responseBody.getClassified().getAddedOn());

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
            public void onFailure(Call<ViewClassifiedOutput> call, Throwable t) {

                hud.dismiss();

            }
        });
    }

    public void Disable(String ad_id) {

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







}

