package com.shopping.techxform.amersouq.activities.ProductDetails;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shopping.techxform.amersouq.Models.All_products;
import com.shopping.techxform.amersouq.Models.CheckoutItem;
import com.shopping.techxform.amersouq.Models.ProductsResponse;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.AddItemToCartRequest;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.Product;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.AddToCartResponse;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.activities.AddToCartActivity;
import com.shopping.techxform.amersouq.activities.PrePaymentPage;
import com.shopping.techxform.amersouq.activities.ProductList.BaseActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class ProductDetailsActivityNew extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private Toolbar mToolbar;
    private Context mContext;
    private ArrayList<String> mResources;

    String prdct_name="";
    ViewPager mViewPager;
    private ProductPagerAdapter mAdapter;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private TextView mMrpTv, mSaveAmount, mDealAmount, addToCart,btn_buy_now;
    ImageView mAddView,mRemoveView;
    private TextView mQuantity;

    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_new);
        getwindowsproperty();
        init();
    }

    private void init() {
        mContext = this;
        Intent intent = getIntent();
        final All_products all_products = (All_products) intent.getSerializableExtra("PRODUCT");
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
        mResources = new ArrayList<>();
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mAddView =findViewById(R.id.add_quantity);
        mQuantity =findViewById(R.id.quantity);
        btn_buy_now =findViewById(R.id.btn_buy_now);
        mRemoveView = findViewById(R.id.quantity_minus);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        mDealAmount = findViewById(R.id.et_deal_amount);
        mMrpTv = findViewById(R.id.et_amount);
        mSaveAmount = findViewById(R.id.et_save_amount);
        addToCart = findViewById(R.id.add_to_cart);
        TextView mName = findViewById(R.id.tv_item_name);
        TextView mDesc = findViewById(R.id.tv_item_description);
        TextView mFull_desc = findViewById(R.id.tv_item_detail);
        mMrpTv.setText(String.format("%s%s", "KD ", all_products.getPrice()));
        mDealAmount.setText(String.format("%s%s", "KD ", all_products.getOffer_price()));
        String savePrice = all_products.getPrice();
        if (!all_products.getPrice().isEmpty() && !all_products.getOffer_price().isEmpty()) {
            savePrice = String.valueOf(Double.parseDouble(all_products.getPrice()) - Double.parseDouble(all_products.getOffer_price()));
        }
        mSaveAmount.setText(String.format("%s%s", "KD ", savePrice));
        mName.setText(all_products.getProduct_name());
        prdct_name=all_products.getProduct_name();
        mDesc.setText(all_products.getShort_desc());
        mFull_desc.setText(all_products.getFull_desc());
        if (all_products.getImages() != null && all_products.getImages().length > 0) {
            mResources.addAll(Arrays.asList(all_products.getImages()));
            mAdapter = new ProductPagerAdapter(this, mResources);
            mViewPager.setAdapter(mAdapter);
            mViewPager.setCurrentItem(0);
            mViewPager.setOnPageChangeListener(this);
            setPageViewIndicator();
        }

        btn_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double unit_price=0;
                int count=1;
                try {
                    count= Integer.parseInt(mQuantity.getText().toString());

                }catch (Exception e){}
                try {
                    unit_price=Double.parseDouble(all_products.getOffer_price());
                }catch (Exception e){}
                if (mQuantity.getText().toString().equals("0")) {
                    new SweetAlertDialog(ProductDetailsActivityNew.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Invalid")
                            .setContentText("Enter valid quantity")
                            .show();

                } else {
                    List<CheckoutItem> checkoutItems = new ArrayList<>();
                    checkoutItems.add(new CheckoutItem(prdct_name, all_products.getProduct_id(),unit_price,count, unit_price*count, all_products.getShort_desc()));
                    Intent intent = new Intent(ProductDetailsActivityNew.this, PrePaymentPage.class);
                    intent.putExtra("item_list", (Serializable) checkoutItems);
                    startActivity(intent);                }

            }
        });
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mQuantity.getText().toString().equals("0")) {
//                if (!mQuantity.getText().toString().trim().isEmpty()){
                    if (Integer.parseInt(mQuantity.getText().toString().trim())>0) {
                        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
                        int uid = sharedPreferences.getInt(Constants.user_id, 0);
                        ArrayList<Product> products = new ArrayList<>();
                        Product product = new Product();
                        product.setProduct_id(all_products.getProduct_id());
                        product.setQuantity(mQuantity.getText().toString().trim());
                        products.add(product);
                        AddItemToCartRequest itemToCartRequest = new AddItemToCartRequest();
                        itemToCartRequest.setOwner_id(String.valueOf(uid));
                        itemToCartRequest.setStatus("0");
                        itemToCartRequest.setProduct(products);
                        addProductItem(itemToCartRequest);


                        }

                }
                else {
                    new SweetAlertDialog(ProductDetailsActivityNew.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Invalid")
                            .setContentText("Enter valid quantity")
                            .show();
                }



            }
        });
        mRemoveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuantity.getText().toString().trim().isEmpty())
                    return;
                int quantity=Integer.parseInt(mQuantity.getText().toString().trim());

                if (quantity>0){
                    quantity= quantity -= 1;
                    mQuantity.setText(String.valueOf(quantity));
                }
            }
        });

        mAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuantity.getText().toString().trim().isEmpty())
                    return;
                int quantity=Integer.parseInt(mQuantity.getText().toString().trim());

                if (quantity>=0){
                    quantity= quantity += 1;
                    mQuantity.setText(String.valueOf(quantity));
                }

            }
        });

    }

    private void setPageViewIndicator() {

//        Log.d("###setPageViewIndicator", " : called");
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);
            params.setMarginStart(4);
            params.setMarginEnd(4);
            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    mViewPager.setCurrentItem(presentPosition);
                    return false;
                }

            });


            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    private void addProductItem(AddItemToCartRequest addItemToCartRequest) {
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);


        Call<AddToCartResponse> call = apiService.add_product_to_cart(addItemToCartRequest);

        call.enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Call<AddToCartResponse> call, retrofit2.Response<AddToCartResponse> response) {
                AddToCartResponse productsResponse=response.body();
                if (productsResponse!=null&&productsResponse.getCode().equalsIgnoreCase("200")){

//                    Toast.makeText(ProductDetailsActivityNew.this, productsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    new SweetAlertDialog(ProductDetailsActivityNew.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Added to Cart")
                            .setContentText(prdct_name+" "+mQuantity.getText().toString())
                            .show();
                }

            }

            @Override
            public void onFailure(Call<AddToCartResponse> call, Throwable t) {


            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        Log.d("###onPageSelected, pos ", String.valueOf(position));
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

        if (position + 1 == dotsCount) {

        } else {

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
