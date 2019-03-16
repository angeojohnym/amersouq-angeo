package com.shopping.techxform.amersouq.fragments.ProductList;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shopping.techxform.amersouq.Models.MyOrdersList;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.adapters.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 09-Dec-18.
 */



public class ProductListFragment extends Fragment {
    Button submit_otp;
    RecyclerView orders_list;
    ProductListAdapter ordersListAdapter;
    Activity activity;
    Context context;
    private List<MyOrdersList> myOrdersLists = new ArrayList<>();
    List<String> all_ads_list=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.orders_fragment_layout, container, false);
        getwindowsproperty();
        return view;

    }

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            //getActivity(). //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orders_list=(RecyclerView)view.findViewById(R.id.orders_list);
        for(int i=0;i<16;i++){

            all_ads_list.add("abc");

        }

        prepareOrderModel();
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(getActivity());
        orders_list.setLayoutManager(mLayoutManager);
        ordersListAdapter = new ProductListAdapter(getActivity(),myOrdersLists);
        orders_list.setAdapter(ordersListAdapter);
    }

    private void prepareOrderModel() {
        MyOrdersList myOrdersList = new MyOrdersList("Redmi (Black,64 GB) (6 GB RAM)", "cacelled" , 0, R.drawable.img_a);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi Note 5 Pro (6 GB RAM)", "Your item has been delivered" , 2, R.drawable.img_b);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi Note 6  (6 GB RAM)",
                "Delivered on Fri, Nov 23rd '18" , 5, R.drawable.img_c);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi note (Black, 64 GB)", "delivered" , 0, R.drawable.img_d);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi note 3(Black)", "Your item has been delivered" , 3, R.drawable.img_e);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi note 4", "cacelled" , 3, R.drawable.img_a);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi  6 Pro  (6 GB RAM)", "Delivered on Fri, Nov 23rd '18" , 0, R.drawable.img_b);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi note 6", "cacelled" , 5, R.drawable.img_c);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi (Black, 64 GB)", "Delivered on Fri, Nov 23rd '18" , 1, R.drawable.img_d);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi note 8", "cacelled" , 3, R.drawable.img_e);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi Note 6  (6 GB RAM)", "cacelled" , 3, R.drawable.img_a);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi note (Black, 64 GB)", "Delivered on Fri, Nov 23rd '18" , 0, R.drawable.img_b);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi note 6 (Black, 64 GB)", "Your item has been delivered" , 5, R.drawable.img_c);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi note (Black, 64 GB)", "delivered" , 1, R.drawable.img_d);
        myOrdersLists.add(myOrdersList);
        myOrdersList = new MyOrdersList("Redmi note 8 Pro (Black)", "cacelled" , 3, R.drawable.img_e);
        myOrdersLists.add(myOrdersList);
    }
}