package com.shopping.techxform.amersouq.activities.ProductList;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.shopping.techxform.amersouq.Models.All_products;
import com.shopping.techxform.amersouq.Models.Product;
import com.shopping.techxform.amersouq.Models.ProductsResponse;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LanguageData.AllLanguage;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LanguageData.LanguagesOutput;
import com.shopping.techxform.amersouq.Utils.SpinnerModel;
import com.shopping.techxform.amersouq.activities.ProductDetails.ProductDetailsActivity;
import com.shopping.techxform.amersouq.activities.ProductDetails.ProductDetailsActivityNew;
import com.shopping.techxform.amersouq.adapters.ProductListAdapterNew;
import com.shopping.techxform.amersouq.listner.RecyclerClick;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ProductListActivityNew extends BaseActivity {

    private RecyclerView mProductRv;
    private Toolbar mToolbar;
    private Context mContext;
    private ProductListAdapterNew mProductListAdapter;
    private ArrayList<All_products> mProductList;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_new);
        getwindowsproperty();
        mContext=this;
        init();
    }

    private void init() {
        mProductList=new ArrayList<>();

        mProductRv=findViewById(R.id.product_recyclerview);
        mToolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar=getSupportActionBar();
        if (mActionBar!=null){
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
        addProductData();
        LinearLayoutManager mLayoutManager=new LinearLayoutManager(mContext);
        mProductRv.setLayoutManager(mLayoutManager);
        mProductRv.setHasFixedSize(true);
        mProductListAdapter=new ProductListAdapterNew(mContext,mProductList, new RecyclerClick() {
            @Override
            public void onClickItem(View view, int position) {
                Intent intent = new Intent(mContext, ProductDetailsActivityNew
                        .class);
                intent.putExtra("PRODUCT",mProductList.get(position));
                startActivity(intent);
            }
        });
        mProductRv.setAdapter(mProductListAdapter);
    }

    private void addProductData() {
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<ProductsResponse> call = apiService.getProductList(0,0);

        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, retrofit2.Response<ProductsResponse> response) {
            ProductsResponse productsResponse=response.body();
            if (productsResponse!=null&&productsResponse.getAll_products()!=null&&productsResponse.getAll_products().size()>0){
                mProductList.clear();
                mProductList.addAll(productsResponse.getAll_products());
                mProductListAdapter.notifyDataSetChanged();
            }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_list_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
