package com.shopping.techxform.amersouq;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shopping.techxform.amersouq.Models.Packages;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.activities.ProductDetails.CustomPagerAdapter;
import com.shopping.techxform.amersouq.activities.ProductList.BaseActivity;
import com.shopping.techxform.amersouq.adapters.PackagePagerAdapter;

import java.util.ArrayList;

public class PackageActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {


    ViewPager mViewPager;
    private PackagePagerAdapter mAdapter;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ArrayList<Packages>mResources;

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
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Package");
        }
        mResources=new ArrayList<>();
        mViewPager = (ViewPager) findViewById(R.id.package_pager);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        addPackageData();
        mAdapter = new PackagePagerAdapter(this, mResources);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);
        setPageViewIndicator();
    }

    private void addPackageData() {
        mResources.add(new Packages());
        mResources.add(new Packages());
        mResources.add(new Packages());
        mResources.add(new Packages());
        mResources.add(new Packages());
        mResources.add(new Packages());
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
