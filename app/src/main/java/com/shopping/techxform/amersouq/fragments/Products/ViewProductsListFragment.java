package com.shopping.techxform.amersouq.fragments.Products;

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

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.adapters.AllAdsAdapter;
import com.shopping.techxform.amersouq.adapters.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 09-Dec-18.
 */




public class ViewProductsListFragment extends Fragment {
    Button submit_otp;
    RecyclerView all_ads;
    AllAdsAdapter allAdsAdapter;
    ProductsAdapter productsAdapter;
    Activity activity;
    Context context;
    List<String> all_ads_list=new ArrayList<>();

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_all_products, container, false);
        getwindowsproperty();
        activity=getActivity();
        context=getActivity();

        all_ads=(RecyclerView)view.findViewById(R.id.all_ads_list);

        for(int i=0;i<16;i++){

            all_ads_list.add("abc");

        }


        productsAdapter=new ProductsAdapter(all_ads_list,activity,R.layout.product_list_item,context);
        all_ads.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        all_ads.setItemAnimator(new DefaultItemAnimator());
        all_ads.setNestedScrollingEnabled(false);
        all_ads.setAdapter(productsAdapter);



        return view;

    }

}