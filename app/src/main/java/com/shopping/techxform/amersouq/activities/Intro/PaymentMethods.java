package com.shopping.techxform.amersouq.activities.Intro;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.adapters.AlertsAdapter;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethods extends TopBarPage {

    RecyclerView cards_list;
    List<String> all_ads_list=new ArrayList<>();
    AlertsAdapter alertsAdapter;
    Activity activity;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_payment_methods);


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_payment_methods, null, false);
        root_layout.addView(contentView, 0);

        cards_list=(RecyclerView)findViewById(R.id.cards_list);
        activity=this;
        context=this;

        for(int i=0;i<4;i++){

            all_ads_list.add("abc");

        }


        alertsAdapter=new AlertsAdapter(all_ads_list,activity,R.layout.payment_card_list_item,context);
        cards_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        cards_list.setItemAnimator(new DefaultItemAnimator());
        cards_list.setNestedScrollingEnabled(false);
        cards_list.setAdapter(alertsAdapter);

    }
}
