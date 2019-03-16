package com.shopping.techxform.amersouq.activities.Packages;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.JsonObject;
import com.shopping.techxform.amersouq.Models.AlertResponse;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiClient;
import com.shopping.techxform.amersouq.RetrofitHelpers.ApiInterface;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.activities.ProductDetails.CustomPagerAdapter;
import com.shopping.techxform.amersouq.activities.ProductList.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;

public class PackageActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    int[] mResources = {R.drawable.img_a, R.drawable.img_b, R.drawable.img_c, R.drawable.img_d,
            R.drawable.img_e, R.drawable.img_a};

    ViewPager mViewPager;
    private CustomPagerAdapter mAdapter;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;


    public  void getwindowsproperty(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        getwindowsproperty();
        init();
    }

    private void init() {
        mViewPager = (ViewPager) findViewById(R.id.package_pager);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        mAdapter = new CustomPagerAdapter(this, mResources);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);
        setPageViewIndicator();
    }

    private void setPageViewIndicator() {
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

    private void addData() {
        ApiInterface apiService =
                ApiClient.getRideClient().create(ApiInterface.class);
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.pref_name, Context.MODE_PRIVATE);
        int uid=sharedPreferences.getInt(Constants.user_id,0);

        Call<JsonObject> call = apiService.view_packages(uid);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                JsonObject productsResponse=response.body();

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {


            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {

    }
}
