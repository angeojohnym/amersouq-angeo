package com.shopping.techxform.amersouq;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shopping.techxform.amersouq.Models.Billing;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.ProductList.BaseActivity;
import com.shopping.techxform.amersouq.adapters.BillingAdapter;
import com.shopping.techxform.amersouq.listner.RecyclerClick;

import java.util.ArrayList;

public class BillingDetailsActivity extends BaseActivity {

    private RecyclerView mCardCycler;
    private BillingAdapter mBillingAdapter;
    private Context mContext;
    private ArrayList<Billing>mBillingArrayList;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_details);
        getwindowsproperty();
        mContext=this;
        init();
    }

    private void init() {
        mBillingArrayList=new ArrayList<>();
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mCardCycler=findViewById(R.id.card_list_recycler);
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Billing details");
        }
        addData();
        BillingAdapter billingAdapter=new BillingAdapter(mContext, mBillingArrayList, new RecyclerClick() {
            @Override
            public void onClickItem(View view, int position) {

            }
        });
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        mCardCycler.setHasFixedSize(false);
        mCardCycler.setLayoutManager(manager);
        mCardCycler.setAdapter(billingAdapter);

    }

    private void addData() {
        mBillingArrayList.add(new Billing());
    }
}
