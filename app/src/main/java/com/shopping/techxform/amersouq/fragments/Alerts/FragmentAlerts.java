package com.shopping.techxform.amersouq.fragments.Alerts;

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
import com.shopping.techxform.amersouq.adapters.AlertsAdapter;
import com.shopping.techxform.amersouq.adapters.OrdersListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 15-Dec-18.
 */



public class FragmentAlerts extends Fragment {
    Button submit_otp;
    RecyclerView alerts_list;
    AlertsAdapter alertsAdapter;
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

        View view = inflater.inflate(R.layout.alerts_list_fragment, container, false);

        activity=getActivity();
        context=getActivity();

        alerts_list=(RecyclerView)view.findViewById(R.id.alerts_list);

        for(int i=0;i<16;i++){

            all_ads_list.add("abc");

        }


        alertsAdapter=new AlertsAdapter(all_ads_list,activity,R.layout.notify_list_item,context);
        alerts_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        alerts_list.setItemAnimator(new DefaultItemAnimator());
        alerts_list.setNestedScrollingEnabled(false);
        alerts_list.setAdapter(alertsAdapter);



        return view;

    }

}