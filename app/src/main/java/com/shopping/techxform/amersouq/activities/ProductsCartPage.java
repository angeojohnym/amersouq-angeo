package com.shopping.techxform.amersouq.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shopping.techxform.amersouq.Models.CheckoutItem;
import com.shopping.techxform.amersouq.Models.cart.CartSingleModel;
import com.shopping.techxform.amersouq.Models.cart.DeleteCartInput;
import com.shopping.techxform.amersouq.Models.cart.UpdateCartInput;
import com.shopping.techxform.amersouq.Models.cart.UpdateCartOutput;
import com.shopping.techxform.amersouq.Models.cart.ViewCartResponse;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.Utils.SpinnerModel;
import com.shopping.techxform.amersouq.adapters.CheckoutAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class ProductsCartPage extends AppCompatActivity {

    List<CheckoutItem> items = new ArrayList<>();
    Activity activity;
    Context context;
    double sub_total = 0;
    String cart_id = "0";
    TextView sub_total_td, total_td, delivery_fee_td, checkout_td;
    RecyclerView all_itemlist;
    CheckoutAdapter checkoutAdapter;
    MaterialSpinner pay_spinner;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_cart_page);
        getwindowsproperty();
        activity = this;
        context = this;
        all_itemlist = (RecyclerView) findViewById(R.id.cart_list);
        sub_total_td = (TextView) findViewById(R.id.sub_total_td);
        total_td = (TextView) findViewById(R.id.total_td);
        delivery_fee_td = (TextView) findViewById(R.id.delivery_fee_td);
        checkout_td = (TextView) findViewById(R.id.proceed_btn);
        pay_spinner = (MaterialSpinner) findViewById(R.id.pay_spinner);
        List<SpinnerModel> pay_list = new ArrayList<>();
        pay_list.add(new SpinnerModel("1", "Cash On Delivery"));

        pay_spinner.setItems(pay_list);
        pay_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                SpinnerModel spinnerModel = (SpinnerModel) item;

            }

        });

        viewCartData();

        checkout_td.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ProductsCartPage.this, SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setContentView(R.layout.dialog_payment_method);
                sweetAlertDialog.setTitle("Confirmation!");
                sweetAlertDialog.setContentText("The product order has been placed successfully");
                sweetAlertDialog.show();
            }
        });
    }

    private void viewCartData() {
        final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        int uid = sharedPreferences.getInt(Constants.user_id, 0);
        Call<ViewCartResponse> call = apiService.view_cart(uid);

        call.enqueue(new Callback<ViewCartResponse>() {
            @Override
            public void onResponse(Call<ViewCartResponse> call, retrofit2.Response<ViewCartResponse> response) {
                hud.dismiss();
                items.clear();
                set_tds();
                all_itemlist.setAdapter(null);

                ViewCartResponse productsResponse = response.body();
                System.out.println(new Gson().toJson(productsResponse));
                if (productsResponse != null && productsResponse.getAll_products() != null && productsResponse.getAll_products().size() > 0) {
//                    Toast.makeText(ProductsCartPage.this,Integer.toString(productsResponse.getAll_products().size()),Toast.LENGTH_SHORT).show();

                    cart_id = productsResponse.getCart_id();
                    ArrayList<CartSingleModel> allProducts = new ArrayList<>();
                    allProducts = productsResponse.getAll_products();
                    for (CartSingleModel products : allProducts) {
                        items.add(new CheckoutItem(products.getTitle(), products.getProductId(), Double.parseDouble(products.getOfferPrice()),
                                Integer.parseInt(products.getQuantity()), Integer.parseInt(products.getQuantity()) * Double.parseDouble(products.getOfferPrice()),
                                ""));
                    }


                    checkoutAdapter = new CheckoutAdapter(items, activity, context, 1);
                    all_itemlist.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                    all_itemlist.setItemAnimator(new DefaultItemAnimator());
                    all_itemlist.setNestedScrollingEnabled(false);
                    all_itemlist.setAdapter(checkoutAdapter);
                    set_tds();

                }
            }

            @Override
            public void onFailure(Call<ViewCartResponse> call, Throwable t) {
                set_tds();

                hud.dismiss();
            }
        });
    }

    public void set_qty(String product_id, int qty) {
        for (int i = 0; i < items.size(); i++) {
            CheckoutItem item = items.get(i);
            if (item.getAd_id().equals(product_id)) {
                item.setCount(qty);
                item.setTotal_price(qty * item.getUnit_price());
                break;
            }

        }
        update_cart(product_id, Integer.toString(qty));

    }

    public void delete_cart(String product_id) {
        final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);

        DeleteCartInput cartInput = new DeleteCartInput(cart_id, product_id);
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        int uid = sharedPreferences.getInt(Constants.user_id, 0);
        Call<UpdateCartOutput> call = apiService.delete_cart(cartInput);

        call.enqueue(new Callback<UpdateCartOutput>() {
            @Override
            public void onResponse(Call<UpdateCartOutput> call, retrofit2.Response<UpdateCartOutput> response) {
                hud.dismiss();
                UpdateCartOutput productsResponse = response.body();
                System.out.println(new Gson().toJson(productsResponse));
                if (response.body().getCode().equals("200")) {

                    Toast.makeText(ProductsCartPage.this, "item removed from cart", Toast.LENGTH_SHORT).show();
                    viewCartData();
                } else {
                    viewCartData();
                }
            }

            @Override
            public void onFailure(Call<UpdateCartOutput> call, Throwable t) {
                viewCartData();
                hud.dismiss();
            }
        });
    }

    public void update_cart(String product_id, String quantity) {
        final KProgressHUD hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .show();
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);

        UpdateCartInput cartInput = new UpdateCartInput(cart_id, quantity, product_id);
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        int uid = sharedPreferences.getInt(Constants.user_id, 0);
        Call<UpdateCartOutput> call = apiService.update_cart(cartInput);

        call.enqueue(new Callback<UpdateCartOutput>() {
            @Override
            public void onResponse(Call<UpdateCartOutput> call, retrofit2.Response<UpdateCartOutput> response) {
                hud.dismiss();
                UpdateCartOutput productsResponse = response.body();
                System.out.println(new Gson().toJson(productsResponse));
                if (response.body().getCode().equals("200")) {

                    Toast.makeText(ProductsCartPage.this, "item updated in cart", Toast.LENGTH_SHORT).show();
//                    viewCartData();
                    set_tds();
                }
            }

            @Override
            public void onFailure(Call<UpdateCartOutput> call, Throwable t) {

                hud.dismiss();
            }
        });
    }

    public void set_tds() {
        sub_total_td.setText(Double.toString(0.0));
        total_td.setText(Double.toString(0.0));

        delivery_fee_td.setText(Double.toString(0.0));

        sub_total = 0;
        for (int i = 0; i < items.size(); i++) {
            CheckoutItem item = items.get(i);
            sub_total = sub_total + item.getTotal_price();
        }
        sub_total_td.setText(Double.toString(sub_total));
        if (sub_total > 0.0) {
            delivery_fee_td.setText(Double.toString(10.0));

            total_td.setText(Double.toString(sub_total + 10));
        } else {
            delivery_fee_td.setText(Double.toString(0.0));

            total_td.setText(Double.toString(0.0));

        }
    }

}
