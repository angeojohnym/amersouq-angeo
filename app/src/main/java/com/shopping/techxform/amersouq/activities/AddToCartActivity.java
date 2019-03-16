package com.shopping.techxform.amersouq.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.shopping.techxform.amersouq.Models.ProductsResponse;
import com.shopping.techxform.amersouq.Models.cart.All_products;
import com.shopping.techxform.amersouq.Models.cart.CartSingleModel;
import com.shopping.techxform.amersouq.Models.cart.ViewCartResponse;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.activities.ProductList.BaseActivity;
import com.shopping.techxform.amersouq.adapters.AddCartAdapter;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class AddToCartActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private AddCartAdapter mAdapter;
    private Toolbar mToolbar;
    private ArrayList<CartSingleModel> mViewCartList;
    private Context mContext;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        getwindowsproperty();

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mContext = this;
        recyclerView = findViewById(R.id.recyclerViewAddToCart);
        mViewCartList = new ArrayList<>();
        mAdapter = new AddCartAdapter(mContext,mViewCartList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        viewCartData();
    }

    //
    private void viewCartData() {
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        int uid = sharedPreferences.getInt(Constants.user_id, 0);
        Call<ViewCartResponse> call = apiService.view_cart(uid);

        call.enqueue(new Callback<ViewCartResponse>() {
            @Override
            public void onResponse(Call<ViewCartResponse> call, retrofit2.Response<ViewCartResponse> response) {
                ViewCartResponse productsResponse = response.body();
                System.out.println(new Gson().toJson(productsResponse));
                if (productsResponse != null && productsResponse.getAll_products() != null && productsResponse.getAll_products().size() > 0) {
                    mViewCartList.clear();
                    mViewCartList.addAll(productsResponse.getAll_products());
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ViewCartResponse> call, Throwable t) {

            }
        });
    }

    public void onClickCheckout(View view) {

        final Dialog dialog = new Dialog(AddToCartActivity.this);
        dialog.setContentView(R.layout.dialog_payment_method);
        Button select = dialog.findViewById(R.id.buttonSelect);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddToCartActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setContentView(R.layout.dialog_payment_method);
                sweetAlertDialog.setTitle("Confirmation!");
                sweetAlertDialog.setContentText("The product order has been placed successfully");
                sweetAlertDialog.show();
            }
        });
        dialog.show();


    }
}
