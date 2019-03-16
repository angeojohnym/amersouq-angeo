package com.shopping.techxform.amersouq.fragments.Orders;

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
import com.shopping.techxform.amersouq.adapters.OrdersListAdapter;
import com.shopping.techxform.amersouq.adapters.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 09-Dec-18.
 */



public class OrdersFragment extends Fragment {
    Button submit_otp;
    RecyclerView orders_list;
    OrdersListAdapter ordersListAdapter;
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

        View view = inflater.inflate(R.layout.orders_fragment_layout, container, false);
        getwindowsproperty();
        activity=getActivity();
        context=getActivity();

        orders_list=(RecyclerView)view.findViewById(R.id.orders_list);

        for(int i=0;i<16;i++){

            all_ads_list.add("abc");

        }


        ordersListAdapter=new OrdersListAdapter(all_ads_list,activity,R.layout.order_list_item,context);
        orders_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        orders_list.setItemAnimator(new DefaultItemAnimator());
        orders_list.setNestedScrollingEnabled(false);
        orders_list.setAdapter(ordersListAdapter);



        return view;

    }

}