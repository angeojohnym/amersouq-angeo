package com.shopping.techxform.amersouq.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.shopping.techxform.amersouq.Models.CheckoutItem;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LanguageData.AllLanguage;
import com.shopping.techxform.amersouq.Utils.SpinnerModel;
import com.shopping.techxform.amersouq.adapters.CheckoutAdapter;
import com.shopping.techxform.amersouq.adapters.HomepageLiveAuctionAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PrePaymentPage extends AppCompatActivity {
List<CheckoutItem> items=new ArrayList<CheckoutItem>();
Activity activity;
Context context;
double sub_total=0;
TextView sub_total_td,total_td,checkout_td;
RecyclerView all_itemlist;
CheckoutAdapter checkoutAdapter;
MaterialSpinner pay_spinner;
TextView delivery_fee_td;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_payment_page);
        getwindowsproperty();
        items = (List<CheckoutItem>) getIntent().getSerializableExtra("item_list");

        activity=this;
        context=this;
        all_itemlist=(RecyclerView)findViewById(R.id.cart_list);
        sub_total_td=(TextView)findViewById(R.id.sub_total_td);
        total_td=(TextView)findViewById(R.id.total_td);
        checkout_td=(TextView)findViewById(R.id.proceed_btn);
        pay_spinner=(MaterialSpinner) findViewById(R.id.pay_spinner);
        delivery_fee_td=(TextView)findViewById(R.id.delivery_fee_td);
        checkoutAdapter = new CheckoutAdapter(items, activity, context,0);
        all_itemlist.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        all_itemlist.setItemAnimator(new DefaultItemAnimator());
        all_itemlist.setNestedScrollingEnabled(false);
        all_itemlist.setAdapter(checkoutAdapter);

        for(CheckoutItem item:items){
            sub_total=sub_total+item.getTotal_price();
        }
        sub_total_td.setText(Double.toString(sub_total));
        total_td.setText(Double.toString(sub_total+10));
        List<SpinnerModel> pay_list = new ArrayList<>();
        pay_list.add(new SpinnerModel("1","Cash On Delivery"));

        pay_spinner.setItems(pay_list);
        pay_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                SpinnerModel spinnerModel = (SpinnerModel) item;
//                lan_id = spinnerModel.getId();
//                language = spinnerModel.getName();
            }

        });
    checkout_td.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PrePaymentPage.this, SweetAlertDialog.SUCCESS_TYPE);
            sweetAlertDialog.setContentView(R.layout.dialog_payment_method);
            sweetAlertDialog.setTitle("Confirmation!");
            sweetAlertDialog.setContentText("The product order has been placed successfully");
            sweetAlertDialog.show();
        }
    });
    }

}
