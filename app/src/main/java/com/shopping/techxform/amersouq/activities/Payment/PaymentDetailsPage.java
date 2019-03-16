package com.shopping.techxform.amersouq.activities.Payment;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.adapters.PackagesAdapter;
import com.shopping.techxform.amersouq.adapters.PaymentListAdapter;

import java.util.ArrayList;
import java.util.List;

public class PaymentDetailsPage extends AppCompatActivity {

    RecyclerView payment_list;
    Context context;
    Activity activity;
    List<String> all_payments=new ArrayList<>();
    PaymentListAdapter paymentListAdapter;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details_page);

        getwindowsproperty();

        activity=this;
        context=this;
        payment_list=(RecyclerView)findViewById(R.id.payment_list);


        for(int i=0;i<9;i++){

            all_payments.add("abc");

        }

        paymentListAdapter=new PaymentListAdapter(all_payments,activity,R.layout.payment_list_item,context);
        payment_list.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        payment_list.setItemAnimator(new DefaultItemAnimator());
        payment_list.setNestedScrollingEnabled(false);
        payment_list.setAdapter(paymentListAdapter);
    }
}
